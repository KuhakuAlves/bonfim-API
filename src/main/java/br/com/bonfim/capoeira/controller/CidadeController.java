package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.CidadeDTO;
import br.com.bonfim.capoeira.model.cadastro.Cidade;
import br.com.bonfim.capoeira.service.cadastro.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    public CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll(){
        List<Cidade> lista = cidadeService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id){
        Cidade cid = cidadeService.findById(id);
        return ResponseEntity.ok().body(cid);
    }

    @GetMapping(value = "/estado")
    public ResponseEntity<List<Cidade>> findCidadesByEstadoId(@RequestParam(value = "estadoId") Long estadoId){
        List<Cidade> lista = cidadeService.findCidadesByEstadoId(estadoId);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<Void> insert (@Valid @RequestBody CidadeDTO dto){
        Cidade cidade = cidadeService.fromDTO(dto);
        cidade = cidadeService.inserir(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update (@PathVariable Long id, @Valid @RequestBody CidadeDTO dto){
        Cidade cidade = cidadeService.fromDTO(dto);
        cidade.setId(id);
        cidade = cidadeService.update(cidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
