package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Cidade;
import br.com.bonfim.capoeira.model.cadastro.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Transactional(readOnly=true)
    public List<Estado> findAllByOrderByNome();
}

