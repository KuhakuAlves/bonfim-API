package br.com.bonfim.capoeira.service.cadastro;

import br.com.bonfim.capoeira.Repository.EstadoRepository;
import br.com.bonfim.capoeira.model.cadastro.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;


    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    public Estado findById(Long id){
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.orElse(null);
    }
}
