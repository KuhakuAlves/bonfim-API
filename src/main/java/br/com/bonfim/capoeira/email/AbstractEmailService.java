package br.com.bonfim.capoeira.email;

import br.com.bonfim.capoeira.model.ContatoModel;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements IEmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailContato(ContatoModel contato){
        try{
           MimeMessage mm = prepareMimeMessageContato(contato);
            sendEmailHtml(mm);
        }catch (MessagingException e) {
            SimpleMailMessage message = preparaEmailContato(contato);
            sendEmail(message);
        }
    }

    protected SimpleMailMessage preparaEmailContato(ContatoModel contatoModel){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Nome");
        message.setText(contatoModel.getNome());
        message.setText("Email");
        message.setText(contatoModel.getEmail());
        message.setText("Telefone");
        message.setText(contatoModel.getTelefone());
        message.setText("Mensagem do cliente");
        message.setText(contatoModel.getMensagem());
        message.setTo(sender);
        message.setFrom(contatoModel.getEmail());
        return message;
    }

    protected MimeMessage prepareMimeMessageContato(ContatoModel obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mm = new MimeMessageHelper(mimeMessage, true);
        mm.setTo(sender);
        mm.setFrom(obj.getEmail());
        mm.setSubject("Contato de cliente pelo website Bonfim!!!");
        mm.setSentDate(new Date(System.currentTimeMillis()));
        mm.setText(htmlFromTemplatePedido(obj), true);
        return mimeMessage;
    }
    protected String htmlFromTemplatePedido(ContatoModel obj) {
        Context context = new Context();
        String imglogo = "/img/capoeirabonfim.jpg";
        context.setVariable("imglogo", imglogo);
        context.setVariable("contatoNome", obj.getNome());
        context.setVariable("contatoEmail", obj.getEmail());
        context.setVariable("contatoTelefone", obj.getTelefone());
        context.setVariable("contatoObservacao", obj.getMensagem());
        return templateEngine.process("email/contato", context);
    }

    @Override
    public void sendNewPasswordEmail(Aluno aluno, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(aluno, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Aluno aluno, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(aluno.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
