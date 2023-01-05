package app.wooportal.server.test.units.core.setup.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import app.wooportal.server.core.messaging.MailConfiguration;
import app.wooportal.server.core.messaging.MailService;
import app.wooportal.server.core.messaging.services.FreemarkerTemplateService;
import freemarker.template.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestMailService {
  
  private MailConfiguration mailConfig;
  
  private MailService service;
  
  private JavaMailSender mailSenderMock;
  
  public TestMailService() {
    mailSenderMock = mock(JavaMailSender.class);
    when(mailSenderMock.createMimeMessage()).thenReturn(
        new MimeMessage(Session.getDefaultInstance(new Properties())));
    
    mailConfig = new MailConfiguration(
        "test@from.de", 
        "test@to.de", 
        false, "/templates/", "portal");
    service = new MailService(
        mailSenderMock,
        mailConfig, 
        new FreemarkerTemplateService(
            new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS), 
            mailConfig));
  }
}
