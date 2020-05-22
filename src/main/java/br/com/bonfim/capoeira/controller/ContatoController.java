package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.ContatoModel;
import br.com.bonfim.capoeira.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/Contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping()
    public ResponseEntity<String> enviarEmail(@Valid @RequestBody ContatoModel contatoModel) throws Exception {
      try{
        contatoService.enviarEmail(contatoModel);
        return  ResponseEntity.ok().body("Email enviado com sucesso");
      }catch(Exception ex){
          throw new Exception(ex);
      }
    }
}
