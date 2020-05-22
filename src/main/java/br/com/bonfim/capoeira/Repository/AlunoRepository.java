package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Transactional(readOnly=true)
    Optional<Aluno> findByEmail(String email);
}
