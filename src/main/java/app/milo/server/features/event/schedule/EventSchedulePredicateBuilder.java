package app.milo.server.features.event.schedule;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class EventSchedulePredicateBuilder extends PredicateBuilder<QEventScheduleEntity, EventScheduleEntity> {

  public EventSchedulePredicateBuilder() {
    super(QEventScheduleEntity.eventScheduleEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.event.videoChatLink.likeIgnoreCase(term);
  }

  public BooleanExpression withEventId(String eventId) {
    return query.event.id.eq(eventId);
  }

  public BooleanExpression startDateBetween(OffsetDateTime begin, OffsetDateTime end) {
    return query.startDate.between(begin, end);
  }

  public BooleanExpression withStartDateLaterThanToday() {
    return query.startDate.after(OffsetDateTime.now()
        .withHour(0)
        .withMinute(0));
  }
}
