package app.milo.server.features.event.attendeeConfiguration;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class EventAttendeeConfigurationPredicateBuilder
    extends PredicateBuilder<QEventAttendeeConfigurationEntity, EventAttendeeConfigurationEntity> {

  public EventAttendeeConfigurationPredicateBuilder() {
    super(QEventAttendeeConfigurationEntity.eventAttendeeConfigurationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
