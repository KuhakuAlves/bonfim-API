package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Long> {
}
