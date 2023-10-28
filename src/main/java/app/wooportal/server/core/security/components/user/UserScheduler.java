package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.wooportal.server.core.security.components.user.passwordReset.PasswordResetService;

@Component
public class UserScheduler {

  private final PasswordResetService passwordResetService;

  private final UserService userService;

  public UserScheduler(PasswordResetService passwordResetService, UserService userService) {
    this.passwordResetService = passwordResetService;
    this.userService = userService;
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void deleteNotVerifiedUsers() {
    var notVerified = userService.readAll(userService
        .collectionQuery(
            userService.getPredicate().createdBefore(OffsetDateTime.now().minusDays(14)))
        .and(userService.getPredicate().notVerified())).getList();

    if (notVerified != null && !notVerified.isEmpty()) {
      userService.deleteAll(notVerified);
    }
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void deletePasswordResets() {
    var notReseted = passwordResetService.getCreatedBefore(OffsetDateTime.now().minusDays(1));

    if (notReseted != null && !notReseted.isEmpty()) {
      passwordResetService.deleteAll(notReseted);
    }
  }

}
