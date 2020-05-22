package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.cadastro.Graduacao;
import br.com.bonfim.capoeira.service.GraduacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/Graduacao")
public class GraduacaoController {
    @Autowired
    private GraduacaoService graduacaoService;

    @GetMapping
    public ResponseEntity<List<Graduacao>> findAllEventos() throws Exception{
        try {
            List<Graduacao> lista = graduacaoService.findAll();
            return new ResponseEntity<List<Graduacao>>(lista, HttpStatus.OK);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Graduacao> findById(@PathVariable Long id) throws Exception {
        try {
            Graduacao model = graduacaoService.findById(id);
            return new ResponseEntity<Graduacao>(model, HttpStatus.OK);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @PostMapping()
    public ResponseEntity<String> salvar(@Valid @RequestBody Graduacao graduacao) throws Exception{
        try {
            graduacaoService.salvar(graduacao);
            return ResponseEntity.ok("Dados salvo com sucesso");
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws EmptyResultDataAccessException, Exception{
        try {
            graduacaoService.deleteById(id);
            return ResponseEntity.ok("Graduação deletada com sucesso");
        }catch(EmptyResultDataAccessException dtEx){
            return ResponseEntity.badRequest().body("Graduacao não existe");
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
