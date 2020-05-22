package br.com.bonfim.capoeira.service;

import br.com.bonfim.capoeira.Repository.EventoRepository;
import br.com.bonfim.capoeira.Util.ImagemUtil;
import br.com.bonfim.capoeira.model.EventoModel;
import br.com.bonfim.capoeira.service.complemento.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private S3Service s3Service;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer imagemSize;

    public List<EventoModel> findAll() throws Exception {
        try {
          return eventoRepository.findAll();
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public EventoModel findById(Long id) throws Exception {
        try {
            Optional<EventoModel> eventoModel = eventoRepository.findById(id);
            return eventoModel.orElse(new EventoModel());
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }

    public void salvar(EventoModel eventoModel) {
        try {
            eventoRepository.save(eventoModel);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public EventoModel salvaFoto(Long id, MultipartFile foto) {
        try {
            EventoModel evento = this.findById(id);
            BufferedImage jpgImage = ImagemUtil.geetJPGImagemFromFile(foto);
            jpgImage = ImagemUtil.cropSquare(jpgImage);
            jpgImage = ImagemUtil.resize(jpgImage, imagemSize);
            String fileName = prefix + evento.getId() + ".jpg";

            URI uri = s3Service.uploadFile(ImagemUtil.getInputStream(jpgImage, "jpg"), fileName, "image");
            evento.setFoto(uri.toString());
            this.salvar(evento);
            return evento;
        } catch (Exception ex) {
           throw new RuntimeException("Erro ao salvar foto no banco");
        }
    }

    public void deletar(Long id)  throws EmptyResultDataAccessException, Exception{
        try {
            Optional<EventoModel> eventoModel = eventoRepository.findById(id);
            if( (eventoModel.isPresent()) && (eventoModel.get().getFoto() != null))
              s3Service.deletarArquivo(eventoModel.get().getFoto());
            eventoRepository.deleteById(id);
        }catch(EmptyResultDataAccessException dtEx){
            throw new EmptyResultDataAccessException("Evento não existe", dtEx.getExpectedSize(), dtEx);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
