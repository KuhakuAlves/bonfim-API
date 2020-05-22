package br.com.bonfim.capoeira.model.DTO;

import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AlunoDTOSaida implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;


    private String nome;

    private String email;

    public AlunoDTOSaida(){

    }

    public AlunoDTOSaida(Aluno aluno){
        id = aluno.getId();
        nome = aluno.getNome();
        email = aluno.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
