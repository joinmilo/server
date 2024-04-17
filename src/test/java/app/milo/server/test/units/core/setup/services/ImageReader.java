package app.milo.server.test.units.core.setup.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
 
  public static byte[] readFile(String path) {
    try {
      BufferedImage image = ImageIO.read(new File(path));
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ImageIO.write(image, getType(path), bos);         
      return bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String getMimeType(String path) {
    return "image/" + getType(path);
  }
  
  public static String getType(String path) {
    return path.split("\\.")[1];
  }
}
