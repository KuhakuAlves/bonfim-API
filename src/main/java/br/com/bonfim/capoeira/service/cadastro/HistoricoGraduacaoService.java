package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.HistoricoGraduacaoRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.DTO.HistoricoGraduacaoDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Graduacao;
import br.com.bonfim.capoeira.model.cadastro.HistoricoGraduacao;
import br.com.bonfim.capoeira.service.GraduacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoGraduacaoService {

    @Autowired
    private GraduacaoService graduacaoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private HistoricoGraduacaoRepository historicoGraduacaoRepository;

    public List<HistoricoGraduacao> findAll(){
        return historicoGraduacaoRepository.findAll();
    }

    public HistoricoGraduacao findById(Long id){
        Optional<HistoricoGraduacao> historicoGraducao = historicoGraduacaoRepository.findById(id);
        return historicoGraducao.orElseThrow(() -> new ObjectNotFoundException("Graduação do Aluno não encontrado"));
    }

    public HistoricoGraduacao findByIdAndAlunoIdAndGraduacaoId(Long id, Long graduacao_id, Long aluno_id){
        Aluno aluno = alunoService.findById(aluno_id);
        Graduacao graduacao = graduacaoService.findById(graduacao_id);
        Optional<HistoricoGraduacao> historicoGraducao = historicoGraduacaoRepository.findByIdAndAlunoIdAndGraduacaoId(id, aluno, graduacao);
        return historicoGraducao.orElseThrow(() -> new ObjectNotFoundException("Graduação do Aluno não encontrado"));
    }

    @Transactional
    public HistoricoGraduacao insert(HistoricoGraduacao historicoGraducao){
        return historicoGraduacaoRepository.save(historicoGraducao);
    }

    public HistoricoGraduacao update(HistoricoGraduacao historicoGraducao){
        return historicoGraduacaoRepository.save(historicoGraducao);
    }

    public void delete(Long id){
        findById(id);
        try{
           historicoGraduacaoRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("Não foi possível excluir a graduação do aluno ");
        }
    }

    public HistoricoGraduacao fromDTO(HistoricoGraduacaoDTO dto){
        Aluno aluno = alunoService.findById(dto.getAlunoId());
        Graduacao graduacao = graduacaoService.findById(dto.getGraduacaoId());
        HistoricoGraduacao hg = new HistoricoGraduacao(aluno, graduacao, dto.isAtiva(), dto.getDtGraduacao());
        return hg;
    }

}
