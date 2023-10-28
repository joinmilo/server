package app.wooportal.server.test.units.core.error;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.messaging.MailService;

public class ErrorServiceTest {
  
  private static MailService mailServiceMock;
  private static ErrorMailService errorService;
  
  @BeforeAll
  public static void init() {
    mailServiceMock = mock(MailService.class);
    errorService = new ErrorMailService(mailServiceMock);
  }
  
  @Test
  public void sendErrorMailWithException() throws Throwable {   
    var test = new Exception("test");
    
    errorService.sendErrorMail(test);
    
    verify(mailServiceMock).sendEmail(
        argThat(subject -> subject.equals("Error")), 
        argThat(template -> template.equals("error.ftl")), 
        argThat(templateModel -> templateModel.containsKey("error") && templateModel.containsKey("metaInfo")),
        any());
  }

}
