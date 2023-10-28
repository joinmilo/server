package app.wooportal.server.core.messaging.notifications.base;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;

@Service
public class NotificationService
    extends DataService<NotificationEntity, NotificationPredicateBuilder> {

  public NotificationService(NotificationRepository repo, NotificationPredicateBuilder entities)
      throws Exception {
    super(repo, entities);

  }
}
