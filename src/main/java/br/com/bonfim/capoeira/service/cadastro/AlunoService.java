package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.AlunoRepository;
import br.com.bonfim.capoeira.Repository.EnderecoAlunoRepository;
import br.com.bonfim.capoeira.Util.ImagemUtil;
import br.com.bonfim.capoeira.exception.AuthorizationException;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.DTO.AlunoDTO;
import br.com.bonfim.capoeira.model.DTO.AlunoNewDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Cidade;
import br.com.bonfim.capoeira.model.cadastro.EnderecoAluno;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.EstadoCivil;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.OrientacaoSexual;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.Perfil;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.TipoCliente;
import br.com.bonfim.capoeira.security.UserDetailsSecurity;
import br.com.bonfim.capoeira.security.UserService;
import br.com.bonfim.capoeira.service.complemento.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EnderecoAlunoRepository enderecoAlunoRepository;

    @Autowired
    private S3Service s3Service;

    @Value("${img.prefix.aluno.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer imagemSize;

    public List<Aluno> findAll (){
        log.info("alunoService.listaAlunos ");
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id){
        log.info("alunoService.findById id: " + id);
        UserDetailsSecurity user = UserService.authenticated();
        if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.orElseThrow(() -> new ObjectNotFoundException("Não é possivel encontrar o Aluno"));
    }

    public Aluno findByEmail(String email){
        log.info("alunoService.findByEmail email: " + email);
        Optional<Aluno> aluno = alunoRepository.findByEmail(email);
        return aluno.orElseThrow(() -> new ObjectNotFoundException("Não é possivel encontrar o Aluno"));
    }

    @Transactional
    public Aluno insert(Aluno aluno){
        log.info("alunoService.insert " + aluno.getId());
        aluno.setId(null);
        aluno.setFoto(null);
        aluno = alunoRepository.save(aluno);
        enderecoAlunoRepository.saveAll(aluno.getEnderecos());
        return aluno;
    }

    private void preencheData(Aluno alu){
        if (alu.getDtCad() == null){
            alu.setDtCad(LocalDateTime.now());
        }
        if (alu.getDtAlte() == null){
            alu.setDtAlte(LocalDateTime.now());
        }
    }

    public Aluno update(Aluno aluno){
        log.info("alunoService.update " + aluno.getId());
        Aluno alu = alunoRepository.findById(aluno.getId()).get();
        updateData(aluno, alu);
        return alunoRepository.save(aluno);
    }

    private void updateData(Aluno aluno, Aluno aluBase){
        aluno.setNome(aluBase.getNome());
        aluno.setEmail(aluBase.getEmail());
    }

    public Aluno salvarFoto(Long id, MultipartFile foto){
        log.info("alunoService.salvarFoto id:  " + id + " foto: " + foto.getName());
        Aluno aluno = this.findById(id);
        BufferedImage jpgImage = ImagemUtil.geetJPGImagemFromFile(foto);
        jpgImage = ImagemUtil.cropSquare(jpgImage);
        jpgImage = ImagemUtil.resize(jpgImage, imagemSize);
        String fileName = prefix + aluno.getId() + ".jpg";

        URI uri = s3Service.uploadFile(ImagemUtil.getInputStream(jpgImage, "jpg"), fileName, "image");
        aluno.setFoto(uri.toString());
        return this.update(aluno);
    }

    public void deletar(Long id){
        log.info("alunoService.deletar id: " + id);
        Aluno aluno = this.findById(id);
        try {
            if ((aluno != null) && (aluno.getFoto() != null)) {
                s3Service.deletarArquivo(aluno.getFoto());
            }
            alunoRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir o aluno  " + aluno.getNome());
        }
    }

    public Page<Aluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return alunoRepository.findAll(pageRequest);
    }

    public Aluno fromDTO(AlunoDTO obj){

        Aluno alu = new Aluno(null,
                obj.getNome(),
                obj.getEmail(),
                pe.encode(obj.getSenha()),
                obj.getApelido(),
                obj.isAtivo(),
                obj.getCpfOuCnpj(),
                TipoCliente.toEnum(obj.getTipo()),
                EstadoCivil.toEnum(obj.getEstadoCivil()),
                OrientacaoSexual.toEnum(obj.getOrientacaoSexual())
        );
        Cidade cid = new Cidade(obj.getCidadeId(), null, null);
        EnderecoAluno end = new EnderecoAluno(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), alu, cid);

        alu.getEnderecos().add(end);
        alu.getTelefones().add(obj.getTelefone1());

        if (obj.getTelefone2() != null){
            alu.getTelefones().add(obj.getTelefone2());
        }

        if (obj.getTelefone3() != null){
            alu.getTelefones().add(obj.getTelefone3());
        }

        return alu;
    }

    public Aluno fromDTO(AlunoNewDTO obj){

        Aluno alu = new Aluno(null,
                          obj.getNome(),
                          obj.getEmail(),
                pe.encode(obj.getSenha()),
                          obj.getApelido(),
                          obj.isAtivo(),
                          obj.getCpfOuCnpj(),
                          TipoCliente.toEnum(obj.getTipo()),
                          EstadoCivil.toEnum(obj.getEstadoCivil()),
                          OrientacaoSexual.toEnum(obj.getOrientacaoSexual())
                );
        Cidade cid = new Cidade(obj.getCidadeId(), null, null);
        EnderecoAluno end = new EnderecoAluno(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), alu, cid);

        alu.getEnderecos().add(end);
        alu.getTelefones().add(obj.getTelefone1());

        if (obj.getTelefone2() != null){
            alu.getTelefones().add(obj.getTelefone2());
        }

        if (obj.getTelefone3() != null){
            alu.getTelefones().add(obj.getTelefone3());
        }

        return alu;
    }
}
