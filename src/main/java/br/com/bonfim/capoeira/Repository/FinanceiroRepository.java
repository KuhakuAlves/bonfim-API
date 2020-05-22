package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {

    @Query("SELECT DISTINCT obj FROM Financeiro obj WHERE obj.aluno = :aluno  and obj.id = :id")
    Optional<Financeiro> findByIdAndAluno(@Param("aluno")Aluno aluno, @Param("id") Long id);
}
