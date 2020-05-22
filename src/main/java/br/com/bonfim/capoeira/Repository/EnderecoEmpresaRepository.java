package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Empresa;
import br.com.bonfim.capoeira.model.cadastro.EnderecoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoEmpresaRepository extends JpaRepository<EnderecoEmpresa, Long> {

    List<EnderecoEmpresa> findByEmpresa(Empresa empresa);
}
