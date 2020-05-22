package br.com.bonfim.capoeira.Util;

import br.com.bonfim.capoeira.exception.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class ImagemUtil {

    public static BufferedImage geetJPGImagemFromFile(MultipartFile foto){
      String ext = FilenameUtils.getExtension(foto.getOriginalFilename());
      if (!"png".equals(ext) && !"jpg".equals(ext)){
          throw new FileException("Somente imagens PNG e JPG são permitidas");
      }
        try {
            BufferedImage img = ImageIO.read(foto.getInputStream());
            if ("png".equals(ext)){
                img = pngToJPG(img);
            }
            return img;
        } catch (IOException e) {
            throw new FileException("erro ao ler arquivo");
        }
    }

    private static BufferedImage pngToJPG(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public static InputStream getInputStream(BufferedImage img, String extension){
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        }catch(IOException ioex){
           throw new FileException("Erro ao ler arquivo");
        }
    }
    public static BufferedImage cropSquare(BufferedImage image) {
        int min = (image.getHeight() <= image.getWidth()) ? image.getHeight() : image.getWidth();
        return Scalr.crop(
                image,
                (image.getWidth() / 2) - (min / 2),
                (image.getHeight() / 2) - (min / 2),
                min,
                min
        );
    }

    public static BufferedImage resize(BufferedImage image, int size){
        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
    }
}
