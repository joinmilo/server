package app.wooportal.server.core.media.image;

import javax.annotation.ManagedBean;
import javax.servlet.ServletContext;

import org.springframework.boot.web.servlet.ServletContextInitializer;

import com.twelvemonkeys.servlet.image.IIOProviderContextListener;

@ManagedBean
public class ImageListener implements ServletContextInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    servletContext.addListener(IIOProviderContextListener.class);
  }
}
