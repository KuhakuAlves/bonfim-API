package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.ParametroConfiguracaoRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.config.ParametroConfiguracao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroConfiguracaoService {

    @Autowired
    private ParametroConfiguracaoRepository parametroConfiguracaoRepository;

    public List<ParametroConfiguracao> findAll() {
        return parametroConfiguracaoRepository.findAll();
    }

    public ParametroConfiguracao findById(Long id){
        Optional<ParametroConfiguracao> par = parametroConfiguracaoRepository.findById(id);
        return par.orElseThrow(() -> new ObjectNotFoundException("Parametro não encontrado, id: " + id));
    }

    @Transactional
    public ParametroConfiguracao insert(ParametroConfiguracao par){
       par.setId(null);
       return parametroConfiguracaoRepository.save(par);
    }

    public  ParametroConfiguracao update (ParametroConfiguracao aula){
        return parametroConfiguracaoRepository.save(aula);
    }

    public void delete(Long id){
        findById(id);
        try{
            parametroConfiguracaoRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possível excluir o parametro");
        }
    }

}
