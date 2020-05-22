package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
