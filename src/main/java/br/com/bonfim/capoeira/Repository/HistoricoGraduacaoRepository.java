package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.Graduacao;
import br.com.bonfim.capoeira.model.cadastro.HistoricoGraduacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoGraduacaoRepository extends JpaRepository<HistoricoGraduacao, Long> {

    @Query("SELECT DISTINCT obj FROM HistoricoGraduacao obj WHERE obj.aluno = :aluno and obj.graduacao = :graduacao and obj.id = :id")
    Optional<HistoricoGraduacao> findByIdAndAlunoIdAndGraduacaoId(@Param("id") Long id, @Param("aluno") Aluno aluno, @Param("graduacao")Graduacao graduacao);


}
