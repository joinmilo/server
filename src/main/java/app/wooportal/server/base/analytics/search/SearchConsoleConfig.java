package app.wooportal.server.base.analytics.search;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "search")
public class SearchConsoleConfig {
  
  private String credentials;
  
  private String host;
  
  private String scope;
}
