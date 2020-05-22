package br.com.bonfim.capoeira.service.complemento;

import br.com.bonfim.capoeira.exception.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cloudfront.model.FieldLevelEncryption;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class S3Service {
  @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;
  public URI uploadFile(MultipartFile multipartFile) {
      try {
          log.info("uploadFile inicinado");
          String fileName = multipartFile.getOriginalFilename();
          InputStream inputStream = multipartFile.getInputStream();
          String contentType = multipartFile.getContentType();
          return uploadFile(inputStream, fileName, contentType);
      } catch (IOException e) {
          throw  new FileException("Erro ade IO "+ e.getMessage());
      }

  }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            s3client.putObject(bucketName, fileName, inputStream, metadata);
            log.info("uploadFile fim");

            return s3client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw  new FileException("Erro ao converter URL para URI");
        }
    }

    public void deletarArquivo(String fileName){
         String nomeFile = fileName.substring(fileName.indexOf(".com/") + 5);
         s3client.deleteObject(bucketName, nomeFile);
    }
}
