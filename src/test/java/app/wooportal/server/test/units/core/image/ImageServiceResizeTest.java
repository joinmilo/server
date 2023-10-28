package app.wooportal.server.test.units.core.image;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.wooportal.server.core.media.base.MediaConfiguration;
import app.wooportal.server.core.media.base.MimeTypeService;
import app.wooportal.server.core.media.image.ImageService;
import app.wooportal.server.test.units.core.setup.services.ImageReader;

public class ImageServiceResizeTest {
  
  private final static String basePath = "src/test/resources/pictures/";
  
  private static ImageService imageService;
  
  private static MimeTypeService mimeTypeService;
  
  @BeforeAll
  public static void init() {
    var imageConfig = new MediaConfiguration();
    imageConfig.setImagesMaxHeight(200);
    imageConfig.setImagesMaxWidth(200);
    
    imageService = new ImageService(imageConfig, null);
  }
  
  @Test
  public void resizeOk() {
    var path = basePath + "test_232x232.jpg";
    var data = ImageReader.readFile(path);
    var mimeType = ImageReader.getMimeType(path);
    
    var result = imageService.resize(data, mimeTypeService.getFileExtension(mimeType));
    
    assertThat(result).isNotEqualTo(data);
  }
  
  @Test
  public void resizeNotNeededOk() {
    var path = basePath + "test_100x100.jpg";
    var data = ImageReader.readFile(path);
    var mimeType = ImageReader.getMimeType(path);
    
    var result = imageService.resize(data, mimeTypeService.getFileExtension(mimeType));
    
    assertThat(result).isEqualTo(data);
  }
  
  @Test
  public void resizeNullParams() {   
    var result = imageService.resize(null, null);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void resizeNullImage() {    
    var result = imageService.resize(null, "test");
    
    assertThat(result).isNull();
  }
  
  @Test
  public void resizeEmptyImage() {    
    var result = imageService.resize(new byte[0], "test");
    
    assertThat(result).isNull();
  }
}
