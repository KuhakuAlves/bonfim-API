package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.config.ParametroConfiguracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroConfiguracaoRepository extends JpaRepository<ParametroConfiguracao, Long> {
}
