package app.milo.server.core.media.image;

import javax.inject.Named;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import com.twelvemonkeys.servlet.image.IIOProviderContextListener;
import jakarta.servlet.ServletContext;

@Named
public class ImageListener implements ServletContextInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    servletContext.addListener(IIOProviderContextListener.class);
  }
}
