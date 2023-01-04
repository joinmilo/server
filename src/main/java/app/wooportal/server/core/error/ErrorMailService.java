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

  public void sendErrorMail(Exception e) throws Throwable {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    sendErrorMail(sw.toString());
  }

  public void sendErrorMail(String stackTrace) throws Throwable {
    sendErrorMail(stackTrace, null);
  }

  public void sendErrorMail(String stackTrace, String metaInfo) throws Throwable {
    mailService.sendEmail("Error", "error.ftl",
        Map.of("error", stackTrace, "metaInfo", metaInfo != null ? metaInfo : ""));
  }

}
