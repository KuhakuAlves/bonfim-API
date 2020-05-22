package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
}
