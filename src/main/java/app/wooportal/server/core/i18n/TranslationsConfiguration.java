package app.wooportal.server.core.i18n;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "translations")
public class TranslationsConfiguration {
  
  private String defaultLocale;
  private String detectUrl;
  private String translateUrl;

}
