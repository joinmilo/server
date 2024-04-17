package app.milo.server.test.units.core.messaging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import app.milo.server.test.units.core.setup.services.TestMailService;

public class MailServiceTest {

  private static TestMailService mailService;

  @BeforeAll
  public static void init() {
    mailService = new TestMailService();
   
  }

  @Test
  public void sendEmailWithTemplate() throws Throwable {
    var paramValue1 = "param1";
    var paramValue2 = "param2";
    var subject = "test";
    var params = Map.of("param1", paramValue1, "param2", paramValue2);
    var result = mailService.getService().sendEmail(subject, "test.ftl", params);

    assertThat(result.get()).isTrue();
    verify(mailService.getMailSenderMock()).send((MimeMessage) argThat((message) -> {
      try {
        assertThat(((MimeMessage) message).getSubject()).contains(subject);
        assertThat(((MimeMessage) message).getSubject())
            .contains(mailService.getGeneralConfig().getPortalName());
        assertThat(((MimeMessage) message).getFrom())
            .anyMatch(address -> ((InternetAddress) address).getAddress()
                .equals(mailService.getMailConfig().getFromAddress()));
        assertThat(((MimeMessage) message).getRecipients(Message.RecipientType.TO))
            .anyMatch(address -> ((InternetAddress) address).getAddress()
                .equals(mailService.getMailConfig().getToAddress()));
        assertThat((String) ((MimeMessage) message).getContent()).contains(paramValue1,
            paramValue2);
      } catch (MessagingException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return true;
    }));
  }

}
