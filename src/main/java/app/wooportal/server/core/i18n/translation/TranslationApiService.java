package app.wooportal.server.core.i18n.translation;

import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import app.wooportal.server.core.i18n.TranslationsConfiguration;
import app.wooportal.server.core.i18n.entities.TranslationDto;

@Service
public class TranslationApiService {
  
  private final TranslationsConfiguration config;
  
  private WebClient translationClient;
  
  public TranslationApiService(
      TranslationsConfiguration config) {
    this.config = config;
    this.translationClient = WebClient.create();
  }
  
  public TranslationDto translate(String text, String target) {
    return translate(text, target, null);
  }

  public TranslationDto translate(String text, String target, String source) {
    return translationClient.method(HttpMethod.POST).uri(createUri())
        .bodyValue(createBody(text, target, source))
        .header("Content-Type", "application/json")
        .header("accept", "text/plain")
        .retrieve()
        .bodyToMono(TranslationDto.class).block();
  }
  
  private URI createUri() {
    return UriComponentsBuilder.fromUriString(config.getTranslateUrl())
        .build().encode().toUri();
  }
  
  private String createBody(String text, String target, String source) {
    var body = new JsonObject();
    
    if (text != null) {
      var textInput = new JsonArray();
      textInput.add(text);
      body.add("text", textInput);
    }
    
    if (target != null) {
      body.addProperty("target_lang", target);
    }
    
    if (source != null) {
      body.addProperty("source_lang", source);
    }
    
    return body.toString();
  }
}
