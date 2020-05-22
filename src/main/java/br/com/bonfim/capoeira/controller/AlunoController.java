package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.DTO.AlunoDTO;
import br.com.bonfim.capoeira.model.DTO.AlunoDTOSaida;
import br.com.bonfim.capoeira.model.DTO.AlunoNewDTO;
import br.com.bonfim.capoeira.model.cadastro.Aluno;
import br.com.bonfim.capoeira.service.cadastro.AlunoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id){
        Aluno aluno = alunoService.findById(id);
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<Aluno> findByEmail(@RequestParam(value = "email") String email){
        Aluno aluno = alunoService.findByEmail(email);
        return ResponseEntity.ok().body(aluno);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AlunoDTOSaida>> findAll(){
        List<Aluno> list = alunoService.findAll();
        List<AlunoDTOSaida> listDto = list.stream().map( x -> new AlunoDTOSaida(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<AlunoDTOSaida>>  findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Aluno> list = alunoService.findPage(page, linesPerPage, orderBy, direction);
        Page<AlunoDTOSaida> listDto = list.map(obj -> new AlunoDTOSaida(obj));
        return ResponseEntity.ok().body(listDto);

    }

    //Post

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody AlunoNewDTO newDTO){
        Aluno aluno = alunoService.fromDTO(newDTO);
        aluno = alunoService.insert(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{id}/foto")
    public ResponseEntity<Void> uploadProfilePicture(@PathVariable Long id, @RequestParam(name="foto") MultipartFile foto) {
        alunoService.salvarFoto(id, foto );
        return ResponseEntity.noContent().build();

    }

    //PUT

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void>  update (@Valid @RequestBody AlunoDTO dto, @PathVariable Long id){
        Aluno aluno = alunoService.fromDTO(dto);
        aluno.setId(id);
        aluno = alunoService.update(aluno);
        return ResponseEntity.noContent().build();
    }

    //DELETE

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
