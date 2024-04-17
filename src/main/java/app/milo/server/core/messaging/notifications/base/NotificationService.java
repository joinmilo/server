package app.milo.server.core.messaging.notifications.base;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;

@Service
public class NotificationService
    extends DataService<NotificationEntity, NotificationPredicateBuilder> {

  public NotificationService(NotificationRepository repo, NotificationPredicateBuilder entities)
      throws Exception {
    super(repo, entities);

  }
}
