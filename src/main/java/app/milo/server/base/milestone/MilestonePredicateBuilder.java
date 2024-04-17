package app.milo.server.base.milestone;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class MilestonePredicateBuilder extends PredicateBuilder<QMilestoneEntity, MilestoneEntity> {

  public MilestonePredicateBuilder() {
    super(QMilestoneEntity.milestoneEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
