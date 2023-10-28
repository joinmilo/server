package app.wooportal.server.core.i18n.translation;

import java.net.URI;
import java.time.Duration;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import app.wooportal.server.core.error.exception.ServiceUnavailableException;
import app.wooportal.server.core.i18n.TranslationsConfiguration;
import app.wooportal.server.core.i18n.entities.TranslationDto;
import reactor.util.retry.Retry;

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
    return translationClient.method(HttpMethod.POST)
        .uri(createTranslationUri())
        .bodyValue(createBody(text, target, source))
        .header("Content-Type", "application/json")
        .header("accept", "text/plain")
        .retrieve()
        .bodyToMono(TranslationDto.class)
        .retryWhen(Retry.backoff(2, Duration.ofSeconds(2))
            .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                throw new ServiceUnavailableException("External translation Service failed to process after max retries",
                        HttpStatus.SERVICE_UNAVAILABLE.value());
            }))
        .block();
}
  
  public String[] detectLanguage(String text) {
    return translationClient.method(HttpMethod.POST).uri(createDetectionUri())
            .bodyValue(createBody(text))
            .header("Content-Type", "application/json")
            .header("accept", "text/plain")
            .retrieve()
            .bodyToMono(String[].class)
            .retryWhen(Retry.backoff(2, Duration.ofSeconds(2))
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                    throw new ServiceUnavailableException("External translation Service failed to process after max retries",
                            HttpStatus.SERVICE_UNAVAILABLE.value());
                }))
            .block();
  }
  
  private URI createTranslationUri() {
    return UriComponentsBuilder.fromUriString(config.getTranslateUrl())
        .build().encode().toUri();
  }
  
  private URI createDetectionUri() {
    return UriComponentsBuilder.fromUriString(config.getDetectUrl())
        .build().encode().toUri();
  }
  
  private String createBody(String text) {
    var body = new JsonObject();
    
    if (text != null) {
      var textInput = new JsonArray();
      textInput.add(text);
      body.add("text", textInput);
    }
    
    return body.toString();
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
