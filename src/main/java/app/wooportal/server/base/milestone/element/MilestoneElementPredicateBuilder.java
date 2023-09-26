package app.wooportal.server.base.milestone.element;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class MilestoneElementPredicateBuilder extends PredicateBuilder<QMilestoneElementEntity, MilestoneElementEntity> {

  public MilestoneElementPredicateBuilder() {
    super(QMilestoneElementEntity.milestoneElementEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
