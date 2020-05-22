package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.AulaRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.cadastro.Aula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public List<Aula> findAll() {
        return aulaRepository.findAll();
    }

    public Aula findById(Long id){
        Optional<Aula> aula = aulaRepository.findById(id);
        return aula.orElseThrow(() -> new ObjectNotFoundException("Aula nçao encontrada, id: " + id));
    }

    @Transactional
    public Aula insert(Aula aula){
       aula.setId(null);
       return aulaRepository.save(aula);
    }

    public  Aula update (Aula aula){
        return aulaRepository.save(aula);
    }

    public void delete(Long id){
        findById(id);
        try{
            aulaRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir uma aula que possui treino");
        }
    }

}
