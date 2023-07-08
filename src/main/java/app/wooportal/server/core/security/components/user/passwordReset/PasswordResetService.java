package app.wooportal.server.core.security.components.user.passwordReset;

import java.time.OffsetDateTime;
import java.util.List;
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
public class PasswordResetService extends DataService<PasswordResetEntity, PasswordResetBuilder> {

  private final GeneralConfiguration config;

  private final MailService mailService;

  public PasswordResetService(DataRepository<PasswordResetEntity> repo,
      PasswordResetBuilder predicate, GeneralConfiguration config, MailService mailService) {
    super(repo, predicate);

    this.config = config;
    this.mailService = mailService;
  }

  public Optional<PasswordResetEntity> getByToken(String name) {
    return repo.findOne(singleQuery(predicate.withToken(name)));
  }

  public List<PasswordResetEntity> getCreatedBefore(OffsetDateTime date) {
    return repo.findAll(collectionQuery(false).and(predicate.createdBefore(date))).getList();
  }

  @Override
  public void preSave(PasswordResetEntity entity, PasswordResetEntity newEntity, JsonNode context) {
    if (newEntity.getToken() == null || newEntity.getToken().isBlank()) {
      newEntity.setToken(generateNewToken());
      addContext("key", context);
      try {
        mailService.sendEmail(
            "Passwort zurücksetzen", "password_reset.ftl", Map.of("portalName",
                config.getPortalName(), "link", createPasswordResetLink(newEntity)),
            newEntity.getUser().getEmail());
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  private String generateNewToken() {
    while (true) {
      var key = StringUtils.randomAlphanumeric(255);
      if (getByToken(key).isEmpty()) {
        return key;
      }
    }
  }

  private String createPasswordResetLink(PasswordResetEntity saved) {
    return config.getHost() + "/user/password/" + saved.getToken();
  }

}
