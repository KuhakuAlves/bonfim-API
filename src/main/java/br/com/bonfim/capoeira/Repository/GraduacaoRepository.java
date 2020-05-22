package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Graduacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduacaoRepository extends JpaRepository<Graduacao, Long> {
}
