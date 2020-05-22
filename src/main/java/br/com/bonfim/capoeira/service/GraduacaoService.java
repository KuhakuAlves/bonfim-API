package br.com.bonfim.capoeira.service;

import br.com.bonfim.capoeira.Repository.GraduacaoRepository;
import br.com.bonfim.capoeira.exception.DataIntegrityException;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.cadastro.Graduacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraduacaoService {

    @Autowired
    private GraduacaoRepository graduacaoRepository;

    public List<Graduacao> findAll() throws Exception {
        try {
            return graduacaoRepository.findAll();
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public Graduacao findById(Long id){
        Optional<Graduacao> graduacaoModel = graduacaoRepository.findById(id);
        return graduacaoModel.orElseThrow(() -> new ObjectNotFoundException("Graduação não encontrada"));
    }

    public void salvar(Graduacao graduacao) {
        try {
            graduacaoRepository.save(graduacao);
        }catch(Exception ex){
            throw new DataIntegrityException(ex.getMessage());
        }
    }

    public void deleteById(Long id) throws EmptyResultDataAccessException, Exception {
        try {
            graduacaoRepository.deleteById(id);
        }catch(EmptyResultDataAccessException dtEx){
            throw new EmptyResultDataAccessException("Graduacao não existe", dtEx.getExpectedSize(), dtEx);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
