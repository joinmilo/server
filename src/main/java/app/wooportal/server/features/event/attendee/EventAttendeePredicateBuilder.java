package app.wooportal.server.features.event.attendee;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventAttendeePredicateBuilder extends PredicateBuilder<QEventAttendeeEntity, EventAttendeeEntity> {

  public EventAttendeePredicateBuilder() {
    super(QEventAttendeeEntity.eventAttendeeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.configuration.events.any().slug.likeIgnoreCase(term);
  }
}
