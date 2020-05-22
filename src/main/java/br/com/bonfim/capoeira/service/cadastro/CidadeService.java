package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.CidadeRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.DTO.CidadeDTO;
import br.com.bonfim.capoeira.model.cadastro.Cidade;
import br.com.bonfim.capoeira.model.cadastro.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    public Cidade findById(Long id){
        Optional<Cidade> cid = cidadeRepository.findById(id);
        return cid.orElseThrow(() -> new ObjectNotFoundException("Cidade não foi encontrada, id: " + id));
    }

    public List<Cidade> findCidadesByEstadoId(Long estadoId){
      return cidadeRepository.findCidadesByEstadoId(estadoId);
    }

    @Transactional
    public Cidade inserir(Cidade obj){
        obj.setId(null);
        return cidadeRepository.save(obj);
    }

    public Cidade update (Cidade obj){
        return cidadeRepository.save(obj);
    }

    public void delete(Long id){
        findById(id);
        try{
            cidadeRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir a Cidade com id: " + id);
        }
    }

    public Cidade fromDTO(CidadeDTO objDTO){
        Estado estado = estadoService.findById(objDTO.getEstadoId());
        Cidade cidade = new Cidade(null, objDTO.getNome(), estado);
        return  cidade;
    }

}
