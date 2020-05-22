package br.com.bonfim.capoeira;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.service.cadastro.AlunoService;
import br.com.bonfim.capoeira.service.complemento.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static  org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoTeste {

    @Autowired
    private DBService dbService;

    @Autowired
    private AlunoService alunoService;

    private boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Test
    public void pesquisandoAlunoByEmail() {
        String email = "ricardo.rodrigues.alves@hotmail.com";
        Aluno aluno = alunoService.findByEmail(email);
        assertThat(aluno).isNotNull();
    }
}
