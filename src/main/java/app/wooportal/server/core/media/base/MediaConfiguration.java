package app.wooportal.server.core.media.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "media")
public class MediaConfiguration {
  
  private Integer imagesMaxHeight;
  
  private Integer imagesMaxWidth;

}
