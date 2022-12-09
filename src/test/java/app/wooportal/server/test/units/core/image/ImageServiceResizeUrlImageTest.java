package app.wooportal.server.test.units.core.image;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.media.image.ImageConfiguration;
import app.wooportal.server.core.media.image.ImageService;
import app.wooportal.server.test.units.core.setup.services.ImageReader;

public class ImageServiceResizeUrlImageTest {
  
  private final static String basePath = "src/test/resources/pictures/";
  
  private static ImageService imageService;
  
  @BeforeAll
  public static void init() {
    var imageConfig = new ImageConfiguration();
    imageConfig.setMaxHeight(200);
    imageConfig.setMaxWidth(200);
    
    var errorService = mock(ErrorMailService.class);
    imageService = new ImageService(imageConfig, errorService);
  }
  
  @Test
  public void resizeUrlImageOk() throws MalformedURLException {
    var path = basePath + "test_232x232.jpg";
    var test = new File(path).toURI().toURL();
    
    var result = imageService.resizeUrlImage(test, ImageReader.getMimeType(path));
    
    assertThat(result).isNotEqualTo(ImageReader.readFile(path));
  }
  
  @Test
  public void resizeUrlImageNotNeededOk() throws MalformedURLException {
    var path = basePath + "test_100x100.jpg";
    var test = new File(path).toURI().toURL();
    
    var result = imageService.resizeUrlImage(test, null);
    
    assertThat(result).isEqualTo(ImageReader.readFile(path));
  }
  
  @Test
  public void resizeUrlImageNotExisting() throws MalformedURLException {
    var test = new URL("http://resizeUrlImageNotExisting.blabla/notExists.jpg");
    
    var result = imageService.resizeUrlImage(test, null);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void resizeUrlImageNullEntity() {   
    var result = catchThrowable(() -> imageService.resizeUrlImage(null, null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

}
