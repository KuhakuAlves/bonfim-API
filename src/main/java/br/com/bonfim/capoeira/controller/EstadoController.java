package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.CidadeDTO;
import br.com.bonfim.capoeira.model.DTO.CidadePEStadoDTO;
import br.com.bonfim.capoeira.model.DTO.EstadoDTO;
import br.com.bonfim.capoeira.model.cadastro.Cidade;
import br.com.bonfim.capoeira.model.cadastro.Estado;
import br.com.bonfim.capoeira.service.cadastro.CidadeService;
import br.com.bonfim.capoeira.service.cadastro.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = service.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{estadoId}/cidades")
    public ResponseEntity<List<CidadePEStadoDTO>> findCidades(@PathVariable Long estadoId) {
        List<Cidade> list = cidadeService.findCidadesByEstadoId(estadoId);
        List<CidadePEStadoDTO> listDto = list.stream().map(obj -> new CidadePEStadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
