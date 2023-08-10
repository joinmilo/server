package app.wooportal.server.core.i18n.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.common.net.HttpHeaders;
import app.wooportal.server.base.configuration.ConfigurationService;
import app.wooportal.server.core.i18n.TranslationsConfiguration;
import app.wooportal.server.core.i18n.components.language.LanguageHeader;

@Service
public class LocaleService {
  
  private final ConfigurationService configurationService;
  
  private TranslationsConfiguration staticConfig;

  public LocaleService(
      @Lazy ConfigurationService configurationService,
      TranslationsConfiguration staticConfig) {
    
    this.configurationService = configurationService;
    this.staticConfig = staticConfig;
  }

  public List<String> getCurrentRequestLocales() {
    var headers = new ArrayList<LanguageHeader>();
    headers.add(new LanguageHeader(getDefaultLocale(), 0.1));
    
    var request = getRequest();
    if (request != null) {
      var requestHeader = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
      
      var extractedLanguages = requestHeader.trim().split(",");
      for (var unprepared : extractedLanguages) {
        headers.add(new LanguageHeader(unprepared));
      }
    }
    
    return headers.stream()
        .sorted((h1, h2) -> Double.compare(h1.getValue(), h2.getValue()))
        .map(langHeader -> langHeader.getLanguage())
        .distinct()
        .collect(Collectors.toList());
  }

  private HttpServletRequest getRequest() {
    var attribs = RequestContextHolder.getRequestAttributes();
    if (attribs instanceof ServletRequestAttributes) {
        return (HttpServletRequest) ((ServletRequestAttributes) attribs).getRequest();
    }
    return null;
  }

  public String getDefaultLocale() {
    return configurationService.readOne(
        configurationService.singleQuery(
            configurationService.getPredicate().withCode("defaultLocale")))
    .map(config -> config.getValue())
    .orElseGet(() -> staticConfig.getDefaultLocale());
  }
}
