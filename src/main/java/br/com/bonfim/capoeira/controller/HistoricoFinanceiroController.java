package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.HistoricoFinanceiroDTOSaida;
import br.com.bonfim.capoeira.model.cadastro.HistoricoFinanceiro;
import br.com.bonfim.capoeira.service.cadastro.HistoricoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historicoFinanceiros")
public class HistoricoFinanceiroController {

    @Autowired
    private HistoricoFinanceiroService historicoFinanceiroService;

    @GetMapping
    public ResponseEntity<List<HistoricoFinanceiroDTOSaida>> findAll(){
        List<HistoricoFinanceiro> lista = historicoFinanceiroService.findAll();
        List<HistoricoFinanceiroDTOSaida> dto = lista.stream().map(x -> new HistoricoFinanceiroDTOSaida(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<HistoricoFinanceiroDTOSaida> findById(@PathVariable Long id){
        HistoricoFinanceiro obj = historicoFinanceiroService.findById(id);
        HistoricoFinanceiroDTOSaida dto = new HistoricoFinanceiroDTOSaida(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody HistoricoFinanceiroDTOSaida dto){
        HistoricoFinanceiro h = historicoFinanceiroService.fromDTO(dto);
        h = historicoFinanceiroService.insert(h);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(h.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody HistoricoFinanceiroDTOSaida dto, @PathVariable Long id){
        HistoricoFinanceiro h = historicoFinanceiroService.fromDTO(dto);
        h.setId(id);
        h = historicoFinanceiroService.update(h);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Long id){
        historicoFinanceiroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
