package app.wooportal.server.core.messaging;

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
@ConfigurationProperties(prefix = "mail")
public class MailConfiguration {

  private String fromAddress;
  private String toAddress;
  private Boolean dbTemplate;
  private String templateLocation;
  private String portalName;
  
}
