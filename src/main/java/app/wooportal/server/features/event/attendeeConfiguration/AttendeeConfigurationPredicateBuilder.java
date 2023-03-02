package app.wooportal.server.features.event.attendeeConfiguration;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AttendeeConfigurationPredicateBuilder
    extends PredicateBuilder<QAttendeeConfigurationEntity, AttendeeConfigurationEntity> {

  public AttendeeConfigurationPredicateBuilder() {
    super(QAttendeeConfigurationEntity.attendeeConfigurationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
