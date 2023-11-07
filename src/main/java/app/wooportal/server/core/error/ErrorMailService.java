package app.wooportal.server.core.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.messaging.MailService;

@Service
public class ErrorMailService {

  private final MailService mailService;

  public ErrorMailService(MailService mailService) {
    this.mailService = mailService;
  }

  public void sendErrorMail(Throwable e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    sendErrorMail(sw.toString());
  }

  public void sendErrorMail(String stackTrace) {
    sendErrorMail(stackTrace, null);
  }

  public void sendErrorMail(String stackTrace, String metaInfo) {
    mailService.sendEmail("Error", "error.ftl",
        Map.of("error", stackTrace, "metaInfo", metaInfo != null ? metaInfo : ""));
  }

}
