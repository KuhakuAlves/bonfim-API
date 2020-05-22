package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.config.ParametroConfiguracao;
import br.com.bonfim.capoeira.service.cadastro.ParametroConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/parametroConfiguracoes")
public class ParametroConfiguracaoController {

    @Autowired
    private ParametroConfiguracaoService parametroConfiguracaoService;

    @GetMapping()
    public ResponseEntity<List<ParametroConfiguracao>> findAll(){
        List<ParametroConfiguracao> lista = parametroConfiguracaoService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParametroConfiguracao> findByID(@PathVariable Long id){
        ParametroConfiguracao pg = parametroConfiguracaoService.findById(id);
        return ResponseEntity.ok().body(pg);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ParametroConfiguracao pg){
        pg.setId(null);
        pg = parametroConfiguracaoService.insert(pg);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pg.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update (@Valid @RequestBody ParametroConfiguracao pg, @PathVariable Long id){
        pg.setId(id);
        pg = parametroConfiguracaoService.update(pg);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        parametroConfiguracaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
