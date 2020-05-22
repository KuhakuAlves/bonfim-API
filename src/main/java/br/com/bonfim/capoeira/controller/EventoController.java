package br.com.bonfim.capoeira.controller;

import br.com.bonfim.capoeira.model.EventoModel;
import br.com.bonfim.capoeira.model.Resultado;
import br.com.bonfim.capoeira.service.EventoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/Evento" )
@Slf4j
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<EventoModel>> findAllEventos() throws Exception{
        try {
            List<EventoModel> lista = eventoService.findAll();
            return new ResponseEntity<List<EventoModel>>(lista, HttpStatus.OK);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoModel> findById(@PathVariable Long id) throws Exception {
        try {

            EventoModel model = eventoService.findById(id);
            //model.
            return new ResponseEntity<EventoModel>(model, HttpStatus.OK);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @PostMapping()
    public ResponseEntity<EventoModel> salvar(@Valid @RequestBody EventoModel eventoModel) throws Exception {
        try {
            eventoService.salvar(eventoModel);
            return new ResponseEntity<EventoModel>(eventoModel, HttpStatus.OK);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<EventoModel> salvarFoto(@PathVariable Long id, @RequestParam(name = "foto") MultipartFile foto) throws Exception{
        try {
            log.info("Entrando no processo salvarFoto id: " + id + ", foto: " + foto.getName());
            return new ResponseEntity<EventoModel>(eventoService.salvaFoto(id, foto), HttpStatus.OK);
        }catch(Exception ex){
            log.error("Ocorreram erros ao tentar salvar a foto");
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resultado> deleteById(@PathVariable Long id) throws Exception {
        try {
            eventoService.deletar(id);
            Resultado result = new Resultado("Evento deletado com sucesso");
            return new ResponseEntity<Resultado>(result, HttpStatus.OK);
        }catch(EmptyResultDataAccessException dtEx){
            Resultado result = new Resultado("Evento não existe");
            return new ResponseEntity<Resultado>(result, HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
