package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.EmpresaRepository;
import br.com.bonfim.capoeira.Repository.EnderecoEmpresaRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.DTO.EmpresaDTO;
import br.com.bonfim.capoeira.model.DTO.EmpresaNewDTO;
import br.com.bonfim.capoeira.model.cadastro.Cidade;
import br.com.bonfim.capoeira.model.cadastro.Empresa;
import br.com.bonfim.capoeira.model.cadastro.EnderecoEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoEmpresaRepository enderecoEmpresaRepository;



    public List<Empresa> findAll(){
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id){
        Optional<Empresa> emp = empresaRepository.findById(id);
        return emp.orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada! Id: " + id));
    }

    public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return empresaRepository.findAll(pageRequest);
    }

    @Transactional
    public Empresa insert(Empresa emp){
        emp.setId(null);
        emp = empresaRepository.save(emp);
        enderecoEmpresaRepository.saveAll(emp.getEnderecos());
        return emp;
    }

    public Empresa update(Empresa emp){
        Empresa newEmp = findById(emp.getId());
        updateData(newEmp, emp);
        return empresaRepository.save(emp);
    }

    private void updateData(Empresa newObj, Empresa emp){
        emp.setRazaoSocial(newObj.getRazaoSocial());
    }

    public void delete(Long id){
        findById(id);
        try{
            empresaRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir a Empresa com id: " + id);
        }
    }

    public Empresa fromDTO(EmpresaDTO objDTO){
        Empresa emp = new Empresa(null,
                objDTO.getRazaoSocial(),
                objDTO.getNomeFantasia(),
                objDTO.getCnpj(),
                objDTO.getInscrEstadual(),
                objDTO.getInscrMuniciapl(),
                objDTO.getCnae()
        );

        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        EnderecoEmpresa endEmp = new EnderecoEmpresa(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), emp, cid);
        emp.getEnderecos().add(endEmp);
        emp.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2()!=null) {
            emp.getTelefones().add(objDTO.getTelefone2());
        }
        if (objDTO.getTelefone3()!=null) {
            emp.getTelefones().add(objDTO.getTelefone3());
        }
        return emp;
    }

    public Empresa fromDTO(EmpresaNewDTO objDTO){
        Empresa emp = new Empresa(null,
                objDTO.getRazaoSocial(),
                objDTO.getNomeFantasia(),
                objDTO.getCnpj(),
                objDTO.getInscrEstadual(),
                objDTO.getInscrMuniciapl(),
                objDTO.getCnae()
        );

        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        EnderecoEmpresa endEmp = new EnderecoEmpresa(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), emp, cid);
        emp.getEnderecos().add(endEmp);
        emp.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2()!=null) {
            emp.getTelefones().add(objDTO.getTelefone2());
        }
        if (objDTO.getTelefone3()!=null) {
            emp.getTelefones().add(objDTO.getTelefone3());
        }
        return emp;
    }
}
