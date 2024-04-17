package app.milo.server.core.captcha;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import app.milo.server.core.error.exception.InvalidCaptchaException;

@Service
public class CaptchaService {

  private final CaptchaConfiguration config;
  
  private final ObjectMapper objectMapper;

  public CaptchaService(
      CaptchaConfiguration config,
      ObjectMapper objectMapper) {
    this.config = config;
    this.objectMapper = objectMapper;
  }

  public void verifyToken(String captchaToken) {
    try {
      var request = HttpRequest.newBuilder()
          .uri(URI.create(config.getVerificationUrl()))
          .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
          .timeout(Duration.ofSeconds(10))
          .POST(BodyPublishers.ofString(createBody(captchaToken))).build();
      
      var response = HttpClient.newBuilder()
          .connectTimeout(Duration.ofSeconds(5))
          .build()
          .send(request, BodyHandlers.ofString());
      
      var captchResponse = objectMapper.readValue(response.body(), CaptchaResponse.class);

      if (!captchResponse.getSuccess()) {
        throw new InvalidCaptchaException("Captcha invalid", captchResponse.getErrorCodes());
      }

    } catch (HttpTimeoutException e) {
      throw new InvalidCaptchaException("Captcha server unreachable");
    } catch (IOException | InterruptedException e) {
      throw new InvalidCaptchaException("Captcha verification error", captchaToken);
    }
  }

  private String createBody(String captchaToken) {
    var sb = new StringBuilder();
    sb.append("response=");
    sb.append(captchaToken);
    sb.append("&secret=");
    sb.append(config.getSecret());
    return sb.toString();
  }
}
