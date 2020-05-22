package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.HistoricoGraduacaoDTO;
import br.com.bonfim.capoeira.model.cadastro.HistoricoGraduacao;
import br.com.bonfim.capoeira.service.cadastro.HistoricoGraduacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historicoGraduacoes")
public class HistoricoGraduacaoController {

    @Autowired
    private HistoricoGraduacaoService historicoGraduacaoService;

    @GetMapping
    public ResponseEntity<List<HistoricoGraduacaoDTO>> findAll(){
        List<HistoricoGraduacao> lista = historicoGraduacaoService.findAll();
        List<HistoricoGraduacaoDTO> dto = lista.stream().map(x -> new HistoricoGraduacaoDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<HistoricoGraduacaoDTO> findById(@PathVariable Long id){
        HistoricoGraduacao obj = historicoGraduacaoService.findById(id);
        HistoricoGraduacaoDTO dto = new HistoricoGraduacaoDTO(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody HistoricoGraduacaoDTO dto){
        HistoricoGraduacao hg = historicoGraduacaoService.fromDTO(dto);
        hg = historicoGraduacaoService.insert(hg);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hg.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody HistoricoGraduacaoDTO dto, @PathVariable Long id){
        HistoricoGraduacao hf = historicoGraduacaoService.fromDTO(dto);
        hf.setId(id);
        hf = historicoGraduacaoService.update(hf);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Long id){
        historicoGraduacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
