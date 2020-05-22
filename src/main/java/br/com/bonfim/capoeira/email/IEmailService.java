package br.com.bonfim.capoeira.email;

import br.com.bonfim.capoeira.model.ContatoModel;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface IEmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendEmailHtml(MimeMessage msg);

    void sendEmailContato(ContatoModel contatoModel);

    void sendNewPasswordEmail(Aluno aluno, String newPass);
}
