package app.wooportal.server.core.push;

import java.io.IOException;
import java.util.Collection;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.messaging.notifications.base.NotificationEntity;
import app.wooportal.server.core.messaging.notifications.base.NotificationService;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class PushService {

  private final FirebasePushService firebasePushService;
  private final NotificationService notificationService;
  private final GraphQlPushService graphQLPushService;
  private final UserService userService;

  public PushService(FirebasePushService firebasePushService,
      NotificationService notificationService, GraphQlPushService graphQlPushService,
      UserService userService) throws IOException {

    this.firebasePushService = firebasePushService;
    this.notificationService = notificationService;
    this.graphQLPushService = graphQlPushService;
    this.userService = userService;
  }

  public void sendGlobalPush(MessageDto message) {
    sendPush(userService.getRepo().findAll(), message);
  }

  public void sendPush(Collection<UserEntity> users, MessageDto message) {
    for (var user : users) {
      sendPush(user, message);
    }
  }

  public void sendPush(UserEntity user, MessageDto message) {
    saveNotification(user, message);
    graphQLPushService.sendPush(user, message);

    firebasePushService.sendPush(user, message);
  }

  private void saveNotification(UserEntity user, MessageDto message) {
    switch (message.getType()) {
      case evaluation:
      case event:
      case deletedUser:
      case global:
      case jobAd:
        var notification = new NotificationEntity();
        notification.setUser(user);
        notification.setRead(false);
        notificationService.save(notification);
        break;
      default:
    }
  }
}

