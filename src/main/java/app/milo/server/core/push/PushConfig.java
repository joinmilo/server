package app.milo.server.core.push;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "push")
public class PushConfig {
  
  private String credentials;
  
  private String typeNews;
  
  private String typeSingleContent;
  
  private String typeNewContent;
  
  private String typeActivityReminder;
}
