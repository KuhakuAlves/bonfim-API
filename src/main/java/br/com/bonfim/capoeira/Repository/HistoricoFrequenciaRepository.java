package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Aula;
import br.com.bonfim.capoeira.model.cadastro.HistoricoFrequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoFrequenciaRepository extends JpaRepository<HistoricoFrequencia, Long> {

    @Query("SELECT DISTINCT obj FROM HistoricoFrequencia obj WHERE obj.aluno = :aluno and obj.aula = :aula and obj.id = :id")
    Optional<HistoricoFrequencia> findByIdAndAlunoIdAndAulaId(@Param("id") Long id, @Param("aluno") Aluno aluno, @Param("aula") Aula aula);
}
