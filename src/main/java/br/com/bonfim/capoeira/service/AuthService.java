package br.com.bonfim.capoeira.service;

import br.com.bonfim.capoeira.Repository.AlunoRepository;
import br.com.bonfim.capoeira.email.IEmailService;
import br.com.bonfim.capoeira.exception.ObjectNotFoundException;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private IEmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

        Optional<Aluno> oAluno = alunoRepository.findByEmail(email);
        if (!oAluno.isPresent()){
            throw new ObjectNotFoundException("Email não encontrado");
        }

        Aluno aluno = oAluno.get();

        String newPass = newPassword();
        aluno.setSenha(pe.encode(newPass));

        alunoRepository.save(aluno);
        emailService.sendNewPasswordEmail(aluno, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (rand.nextInt(10) + 48);
        }
        else if (opt == 1) { // gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        }
        else { // gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
