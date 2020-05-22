package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.HistoricoFinanceiroRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.DTO.HistoricoFinanceiroDTOSaida;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Financeiro;
import br.com.bonfim.capoeira.model.cadastro.HistoricoFinanceiro;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.StatusFinanceiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoFinanceiroService {

    @Autowired
    private HistoricoFinanceiroRepository historicoFinanceiroRepository;

    @Autowired
    private FinanceiroService financeiroService;

    public List<HistoricoFinanceiro> findAll(){
        return historicoFinanceiroRepository.findAll();
    }

    public HistoricoFinanceiro findById(Long id){
        Optional<HistoricoFinanceiro> historicoFinanceiro = historicoFinanceiroRepository.findById(id);
        return historicoFinanceiro.orElseThrow(() -> new ObjectNotFoundException("Registro financeiro não encontrado"));
    }

    public HistoricoFinanceiro findByIdAndFinanceiroIdAndAlunoId(Long id, Long financeiro_id, Long aluno_Id){
        Financeiro financeiro = financeiroService.findByIdAndAluno(financeiro_id, aluno_Id);
        Optional<HistoricoFinanceiro> historicoFinanceiro = historicoFinanceiroRepository.findByIdAndFinanceiroIdAndAlunoId(financeiro.getAluno(), financeiro, id);
        return historicoFinanceiro.orElseThrow(() -> new ObjectNotFoundException("Registro financeiro não encontrado"));
    }

    @Transactional
    public HistoricoFinanceiro insert(HistoricoFinanceiro historicoFinanceiro){
        return historicoFinanceiroRepository.save(historicoFinanceiro);
    }

    public HistoricoFinanceiro update(HistoricoFinanceiro historicoFinanceiro){
        return historicoFinanceiroRepository.save(historicoFinanceiro);
    }

    public void delete(Long id){
        HistoricoFinanceiro historicoFinanceiro = findById(id);
        try{
          historicoFinanceiroRepository.delete(historicoFinanceiro);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir a Empresa com id: " + id);
        }
    }

    public HistoricoFinanceiro fromDTO(HistoricoFinanceiroDTOSaida dto){
        Financeiro financeiro = financeiroService.findById(dto.getFinanceiroId());
        HistoricoFinanceiro historicoFinanceiro = new HistoricoFinanceiro(financeiro, financeiro.getAluno(), dto.getDtPagto(), dto.getVlPagto(), StatusFinanceiro.toEnum(dto.getStatusFunanceiro()));
        return historicoFinanceiro;
    }

}
