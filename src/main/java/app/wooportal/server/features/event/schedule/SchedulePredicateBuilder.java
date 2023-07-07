package app.wooportal.server.features.event.schedule;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SchedulePredicateBuilder extends PredicateBuilder<QScheduleEntity, ScheduleEntity> {

  public SchedulePredicateBuilder() {
    super(QScheduleEntity.scheduleEntity);
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
