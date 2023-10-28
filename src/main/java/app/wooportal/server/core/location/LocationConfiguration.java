package app.wooportal.server.core.location;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * The Class LocationConfiguration.
 * 
 * @author Valmir Etemi
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "location")
public class LocationConfiguration {
  
  private String serviceSubscriptionKey;
  private String addressUrl;
  private String routesUrl;
}
