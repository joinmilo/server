package app.wooportal.server.core.hcaptcha;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {

  @Value("${hCaptcha.secret.key}")
  private String hCaptchaSecretKey;


  @PostMapping(value = "/feedback")
  public boolean signup(@RequestParam(name = "name", required = true) String name,
      @RequestParam(name = "email", required = true) String email,
      @RequestParam("h-captcha-response") String captchaResponse)
      throws IOException, InterruptedException {
    if (StringUtils.hasText(captchaResponse)) {

      var sb = new StringBuilder();
      sb.append("response=");
      sb.append(captchaResponse);
      sb.append("&secret=");
      sb.append(this.hCaptchaSecretKey);
      
      HttpRequest request =
          HttpRequest.newBuilder().uri(URI.create("https://hcaptcha.com/siteverify"))
              .header("Content-Type", "application/x-www-form-urlencoded")
              .timeout(Duration.ofSeconds(10)).POST(BodyPublishers.ofString(sb.toString())).build();

      HttpResponse<String> response = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5))
          .build().send(request, BodyHandlers.ofString());

      System.out.println("http response status: " + response.statusCode());
      System.out.println("body: " + response.body());

      if (response.statusCode() == 200) {
        return true;
      }
    }
    return false;
  }
}
