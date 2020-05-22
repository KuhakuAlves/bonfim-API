package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.HistoricoFrequenciaDTO;
import br.com.bonfim.capoeira.model.cadastro.HistoricoFrequencia;
import br.com.bonfim.capoeira.service.cadastro.HistoricoFrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historicoFrequencias")
public class HistoricoFrequenciaController {

    @Autowired
    private HistoricoFrequenciaService historicoFrequenciaService;

    @GetMapping
    public ResponseEntity<List<HistoricoFrequenciaDTO>> findAll(){
        List<HistoricoFrequencia> lista = historicoFrequenciaService.findAll();
        List<HistoricoFrequenciaDTO> dto = lista.stream().map(x -> new HistoricoFrequenciaDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<HistoricoFrequenciaDTO> findById(@PathVariable Long id){
        HistoricoFrequencia obj = historicoFrequenciaService.findById(id);
        HistoricoFrequenciaDTO dto = new HistoricoFrequenciaDTO(obj);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody HistoricoFrequenciaDTO dto){
        HistoricoFrequencia hf = historicoFrequenciaService.fromDTO(dto);
        hf = historicoFrequenciaService.insert(hf);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hf.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody HistoricoFrequenciaDTO dto, @PathVariable Long id){
        HistoricoFrequencia hf = historicoFrequenciaService.fromDTO(dto);
        hf.setId(id);
        hf = historicoFrequenciaService.update(hf);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Long id){
        historicoFrequenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
