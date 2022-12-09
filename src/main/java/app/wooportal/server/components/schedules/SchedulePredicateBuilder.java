package app.wooportal.server.components.schedules;

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
    return query.event.link.likeIgnoreCase(term);
  }
}
