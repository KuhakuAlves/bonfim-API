package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Financeiro;
import br.com.bonfim.capoeira.model.cadastro.HistoricoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoFinanceiroRepository extends JpaRepository<HistoricoFinanceiro, Long> {

    @Query("SELECT DISTINCT obj FROM HistoricoFinanceiro obj WHERE obj.aluno = :aluno and obj.financeiro = :financeiro and obj.id = :id")
    Optional<HistoricoFinanceiro> findByIdAndFinanceiroIdAndAlunoId(@Param("aluno") Aluno aluno, @Param("aluno") Financeiro financeiro, @Param("id") Long id);
}
