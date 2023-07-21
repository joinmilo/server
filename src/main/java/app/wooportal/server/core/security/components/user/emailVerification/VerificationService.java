package app.wooportal.server.core.security.components.user.emailVerification;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.messaging.MailService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.StringUtils;

@Service
public class VerificationService
    extends DataService<VerificationEntity, VerificationPredicateBuilder> {

  private final GeneralConfiguration config;

  private final MailService mailService;

  public VerificationService(DataRepository<VerificationEntity> repo,
      VerificationPredicateBuilder predicate, GeneralConfiguration config,
      MailService mailService) {
    super(repo, predicate);

    this.config = config;
    this.mailService = mailService;
  }

  public Optional<VerificationEntity> getByKey(String name) {
    return repo.findOne(singleQuery(predicate.withToken(name)));
  }

  @Override
  public void preSave(VerificationEntity entity, VerificationEntity newEntity, JsonNode context) {
    if (newEntity.getToken() == null || newEntity.getToken().isBlank()) {
      newEntity.setToken(generateTokenKey());
      addContext("key", context);
      try {
        mailService.sendEmail("Email verifizieren", "verification.ftl",
            Map.of(
                "portalName", config.getPortalName(),
                "link", createVerifcationLink(newEntity)),
            newEntity.getUser().getEmail());
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  private String generateTokenKey() {
    while (true) {
      var token = StringUtils.randomAlphanumeric(255);
      if (getByKey(token).isEmpty()) {
        return token;
      }
    }
  }

  private String createVerifcationLink(VerificationEntity saved) {
    return config.getHost() + "/account/verification/" + saved.getToken();
  }

}
