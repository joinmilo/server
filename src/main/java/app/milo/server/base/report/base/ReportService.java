package app.milo.server.base.report.base;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.core.base.DataService;
import app.milo.server.core.captcha.CaptchaService;
import app.milo.server.core.config.GeneralConfiguration;
import app.milo.server.core.error.exception.BadParamsException;
import app.milo.server.core.messaging.MailService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.user.UserService;

@Service
public class ReportService extends DataService<ReportEntity, ReportPredicateBuilder> {

  private final CaptchaService captchaService;

  private final UserService userService;

  private final MailService mailService;

  private final GeneralConfiguration config;

  public ReportService(DataRepository<ReportEntity> repo,
      ReportPredicateBuilder predicate,
      CaptchaService captchaService,
      UserService userService,
      MailService mailService,
      GeneralConfiguration config) {
    super(repo, predicate);

    this.captchaService = captchaService;
    this.userService = userService;
    this.mailService = mailService;
    this.config = config;
  }

  @Override
  public void preSave(ReportEntity entity, ReportEntity newEntity, JsonNode context) {

    // TODO: Make an hook for specific validations
    if (newEntity.getCaptchaToken() != null && !newEntity.getCaptchaToken().isEmpty()) {
      captchaService.verifyToken(newEntity.getCaptchaToken());
    } else {
      throw new BadParamsException("Captcha token empty or null", null);
    }
  }

  @Override
  public void postCreate(ReportEntity entity, ReportEntity newEntity,
      ReportEntity saved, JsonNode context) {
        this.userService.getUsersWithPrivileges("report_admin", "admin").stream().forEach(user -> {
      try {
        mailService.sendEmail("Neues Feedback", "newReport.ftl",
            Map.of(
                "userName" , user.getFirstName() != null ? " " + user.getFirstName() : "",
                "portalName", config.getPortalName(),
                "link", createLink()),
            user.getEmail());
      } catch (Throwable e) {
        e.printStackTrace();
      }
    });
  }

  private String createLink() {
    return config.getHost() + "/admin/reports";
  }
}


