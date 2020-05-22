package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.FinanceiroRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.DTO.FinanceiroDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Financeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroService {

    @Autowired
    private FinanceiroRepository financeiroRepository;

    @Autowired
    private AlunoService alunoService;

    public List<Financeiro> findAll(){
        return financeiroRepository.findAll();
    }


    public Financeiro findByIdAndAluno(Long id, Long alunoId){
        Aluno aluno = alunoService.findById(alunoId);
        Optional<Financeiro> financeiro = financeiroRepository.findByIdAndAluno(aluno, id);
        return financeiro.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado"));
    }

    public Financeiro findById(Long id){
        Optional<Financeiro> financeiro = financeiroRepository.findById( id);
        return financeiro.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado"));
    }

    @Transactional
    public Financeiro inserir(Financeiro financeiro){
      return financeiroRepository.save(financeiro);
    }

    public Financeiro update(Financeiro financeiro){
      return financeiroRepository.save(financeiro);
    }

    public void delete(Long id){

      findById(id);
      try{
          financeiroRepository.deleteById(id);
      }catch(DataIntegrityViolationException e){
          throw  new DataIntegrityException("Não é possível excluir o registro financeiro");
      }
    }

    public Financeiro fromDto(FinanceiroDTO dto){
        Aluno aluno = alunoService.findById(dto.getAlunoId());
        Financeiro financeiro = new Financeiro(aluno, dto.getVlMensalidade(), dto.getDtVencimento());
        return financeiro;
    }
}
