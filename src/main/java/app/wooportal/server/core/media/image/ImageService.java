package app.wooportal.server.core.media.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.media.base.MediaConfiguration;

@Service
public class ImageService {

  private final MediaConfiguration config;

  private final ErrorMailService errorService;


  public ImageService(MediaConfiguration config, ErrorMailService errorService) {
    this.config = config;
    this.errorService = errorService;
  }

  public byte[] resize(byte[] data, String formatType) {
    if (data != null && data.length > 0 && formatType != null && !formatType.isBlank()) {
      var inputStream = new ByteArrayInputStream(data);
      try {
        var imageBuff = ImageIO.read(inputStream);
        return needsResize(imageBuff) ? resize(imageBuff, formatType) : data;
      } catch (IOException e) {
        errorService.sendErrorMail(e);
        e.printStackTrace();
      }
    }
    return null;
  }

  public byte[] resizeUrlImage(URL url, String formatType) {
    if (url == null) {
      throw new IllegalArgumentException("Image url must not be null");
    }
    try {
      var imageBuff = ImageIO.read(url);
      if (imageBuff != null) {
        formatType = formatType != null && !formatType.isBlank() ? formatType
            : extractFormatFromUrl(url.getPath());
        return needsResize(imageBuff)
            ? resize(imageBuff, formatType)
            : convertToByte(imageBuff, formatType);
      }
    } catch (IOException e) {
      errorService.sendErrorMail(e);
      e.printStackTrace();
    }
    return null;
  }

  public String extractFormatFromUrl(String imageUrl) {
    var splitUrl = imageUrl.split("\\.");
    return splitUrl[splitUrl.length - 1];
  }

  private boolean needsResize(BufferedImage imageBuff) {
    return imageBuff.getHeight() >= config.getImagesMaxHeight()
        || imageBuff.getWidth() >= config.getImagesMaxWidth();
  }

  private byte[] resize(BufferedImage imageBuff, String formatType) throws IOException {
    return convertToByte(
        Scalr.resize(imageBuff, Method.ULTRA_QUALITY, config.getImagesMaxWidth(), config.getImagesMaxHeight()),
        formatType);
  }

  private byte[] convertToByte(BufferedImage image, String mimeType) throws IOException {
    var outputStream = new ByteArrayOutputStream();
    ImageIO.write(image, mimeType, outputStream);
    return outputStream.toByteArray();
  }
}
