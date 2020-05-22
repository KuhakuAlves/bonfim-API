package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.EmpresaDTO;
import br.com.bonfim.capoeira.model.DTO.EmpresaDTOSaida;
import br.com.bonfim.capoeira.model.DTO.EmpresaNewDTO;
import br.com.bonfim.capoeira.model.cadastro.Empresa;
import br.com.bonfim.capoeira.service.cadastro.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<EmpresaDTOSaida>> findAll(){
        List<Empresa> lista = empresaService.findAll();
        List<EmpresaDTOSaida> dto = lista.stream().map(x -> new EmpresaDTOSaida(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<EmpresaDTOSaida>>  findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Empresa> list = empresaService.findPage(page, linesPerPage, orderBy, direction);
        Page<EmpresaDTOSaida> listDto = list.map(obj -> new EmpresaDTOSaida(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable Long id){
        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(empresa);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody EmpresaNewDTO newDTO){
        Empresa empresa = empresaService.fromDTO(newDTO);
        empresa = empresaService.insert(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void>  update (@Valid @RequestBody EmpresaDTO dto, @PathVariable Long id){
        Empresa empresa = empresaService.fromDTO(dto);
        empresa.setId(id);
        empresa = empresaService.update(empresa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
