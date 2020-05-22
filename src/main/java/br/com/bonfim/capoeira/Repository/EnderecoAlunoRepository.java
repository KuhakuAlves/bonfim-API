package br.com.bonfim.capoeira.Repository;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.model.cadastro.EnderecoAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoAlunoRepository extends JpaRepository<EnderecoAluno, Long> {

    List<EnderecoAluno> findByAluno(Aluno aluno);
}
