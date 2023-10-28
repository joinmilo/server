package app.wooportal.server.core.messaging.notifications.base;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NotificationPredicateBuilder
    extends PredicateBuilder<QNotificationEntity, NotificationEntity> {

  public NotificationPredicateBuilder() {
    super(QNotificationEntity.notificationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
