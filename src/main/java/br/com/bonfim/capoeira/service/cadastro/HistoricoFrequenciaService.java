package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.HistoricoFrequenciaRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;

import br.com.bonfim.capoeira.model.DTO.HistoricoFrequenciaDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Aula;
import br.com.bonfim.capoeira.model.cadastro.HistoricoFrequencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoFrequenciaService {

    @Autowired
    private HistoricoFrequenciaRepository historicoFrequenciaRepository;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private AlunoService alunoService;

    public List<HistoricoFrequencia> findAll(){
        return historicoFrequenciaRepository.findAll();
    }

    public HistoricoFrequencia findById(Long id){
        Optional<HistoricoFrequencia> historicoFrequencia = historicoFrequenciaRepository.findById(id);
        return historicoFrequencia.orElseThrow(() -> new ObjectNotFoundException("Não encontrado o hstórico de frequencia"));
    }

    public HistoricoFrequencia findByIdAndAlunoIdAndAulaId(Long id, Long aula_id, Long aluno_id){

        Aluno aluno = alunoService.findById(aluno_id);
        Aula aula = aulaService.findById(aula_id);
        Optional<HistoricoFrequencia> historicoFrequencia = historicoFrequenciaRepository.findByIdAndAlunoIdAndAulaId(id, aluno, aula);
        return historicoFrequencia.orElseThrow(() -> new ObjectNotFoundException("Não encontrado o hstórico de frequencia"));
    }

    @Transactional
    public HistoricoFrequencia insert(HistoricoFrequencia historicoFrequencia){
        return historicoFrequenciaRepository.save(historicoFrequencia);
    }

    public HistoricoFrequencia update(HistoricoFrequencia historicoFrequencia){
        return historicoFrequenciaRepository.save(historicoFrequencia);
    }

    public void delete(Long id){
        findById(id);
        try{
            historicoFrequenciaRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir a Empresa com id: " + id);
        }
    }

    public HistoricoFrequencia fromDTO(HistoricoFrequenciaDTO dto){
        Aluno aluno = alunoService.findById(dto.getAlunoId());
        Aula aula = aulaService.findById(dto.getAulaId());
        HistoricoFrequencia hf = new HistoricoFrequencia(aluno, aula, dto.getDtOcorrencia(), dto.isAtivo());
        return hf;
    }
}
