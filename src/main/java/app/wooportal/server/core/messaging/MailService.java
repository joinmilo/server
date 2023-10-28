package app.wooportal.server.core.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.messaging.template.TemplateService;

@Service
public class MailService {

  private final JavaMailSender sender;

  private final MailConfiguration mailConfig;
  
  private final GeneralConfiguration generalConfig;

  private final TemplateService templateService;

  public MailService(
      JavaMailSender sender,
      MailConfiguration mailConfig,
      GeneralConfiguration generalConfig,
      TemplateService templateService) {
    this.sender = sender;
    this.mailConfig = mailConfig;
    this.generalConfig = generalConfig;
    this.templateService = templateService;
  }

  @Async
  public CompletableFuture<Boolean> sendEmail(String subject, String template,
      Map<String, String> templateModel, String... to) {
    try {
      var model = new HashMap<String, String>(templateModel);
//      model.put("portalName", mailConfig.getPortalName()); //TODO: Mailconfig?
      return sendEmail(subject,
          templateService.createMessage(template, model, new LanguageEntity()), to);

    } catch (Throwable e) {
      e.printStackTrace();
      return CompletableFuture.completedFuture(false);
    }
  }

  @Async
  public CompletableFuture<Boolean> sendEmail(String subject, String content, String... to)
      throws MessagingException {
    try {
      sendEmail(mailConfig.getFromAddress(), subject, content, true,
          to == null || to.length == 0 ? new String[] {mailConfig.getToAddress()} : to);
      return CompletableFuture.completedFuture(true);
    } catch (Exception e) {
      return CompletableFuture.completedFuture(false);
    }

  }

  public void sendEmail(String fromAddress, String subject, String content, boolean html,
      String... toAddresses) throws MessagingException {
    subject = "[" + generalConfig.getPortalName() + "] - " + subject;
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setFrom(fromAddress);
    helper.setTo(toAddresses);
    helper.setSubject(subject);
    helper.setText(content, html);
    sender.send(message);
  }

}
