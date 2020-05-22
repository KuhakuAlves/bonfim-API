package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.cadastro.Aula;
import br.com.bonfim.capoeira.service.cadastro.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {


    @Autowired
    private AulaService aulaService;

    @GetMapping()
    public ResponseEntity<List<Aula>> findAll(){
      List<Aula> lista = aulaService.findAll();
      return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> findById(@PathVariable Long id){
        Aula aula = aulaService.findById(id);
        return ResponseEntity.ok().body(aula);
    }

    @PostMapping
    public ResponseEntity<Void> insert (@Valid @RequestBody Aula aula){
        aula.setId(null);
        aula = aulaService.insert(aula);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(aula.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update (@Valid @RequestBody Aula aula, @PathVariable Long id){
        aula.setId(id);
        aula = aulaService.update(aula);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        aulaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
