package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.FinanceiroDTO;
import br.com.bonfim.capoeira.model.cadastro.Financeiro;
import br.com.bonfim.capoeira.service.cadastro.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController{

    @Autowired
    private FinanceiroService financeiroService;

    @GetMapping
    public ResponseEntity<List<Financeiro>> findAll(){
        List<Financeiro> lista = financeiroService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Financeiro> findById(@PathVariable Long id){
        Financeiro financeiro = financeiroService.findById(id);
        return ResponseEntity.ok().body(financeiro);
    }

    @PostMapping
    public  ResponseEntity<Void> insert(@Valid @RequestBody FinanceiroDTO dto){
        Financeiro financeiro = financeiroService.fromDto(dto);
        financeiro = financeiroService.inserir(financeiro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(financeiro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody FinanceiroDTO dto, @PathVariable Long id){
        Financeiro financeiro = financeiroService.fromDto(dto);
        financeiro.setId(id);
        financeiro = financeiroService.update(financeiro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value ="/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        financeiroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
