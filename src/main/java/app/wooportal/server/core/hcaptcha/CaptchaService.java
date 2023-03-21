package app.wooportal.server.core.hcaptcha;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.error.exception.InvalidCaptchaException;

@Service
public class CaptchaService {

  private final CaptchaConfiguration config;

  public CaptchaService(CaptchaConfiguration config) {
    this.config = config;
  }

  public void verifyToken(String captchaResponse) {

    var sb = new StringBuilder();
    sb.append("response=");
    sb.append(captchaResponse);
    sb.append("&secret=");
    sb.append(config.getSecret());

    HttpRequest request =
        HttpRequest.newBuilder().uri(URI.create("https://hcaptcha.com/siteverify"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .timeout(Duration.ofSeconds(10)).POST(BodyPublishers.ofString(sb.toString())).build();

    HttpResponse<String> response;
    try {
      response = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build().send(request,
          BodyHandlers.ofString());

      if (response.body().contains("\"success\":false")) {
        throw new InvalidCaptchaException("Captcha invalid", response.body());
      }
    } catch (IOException | InterruptedException e) {

      throw new InvalidCaptchaException("Captcha verification error", request);
    }
  }
}
