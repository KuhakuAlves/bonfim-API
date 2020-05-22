package br.com.bonfim.capoeira.service;


import br.com.bonfim.capoeira.email.IEmailService;
import br.com.bonfim.capoeira.model.ContatoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired
    private IEmailService emailService;

    public void enviarEmail(ContatoModel contatoModel) throws Exception{
        emailService.sendEmailContato(contatoModel);
    }
}
