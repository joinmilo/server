package app.wooportal.server.features.event.attendee;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AttendeePredicateBuilder extends PredicateBuilder<QAttendeeEntity, AttendeeEntity> {

  public AttendeePredicateBuilder() {
    super(QAttendeeEntity.attendeeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.configuration.events.any().name.likeIgnoreCase(term);
  }
}
