package app.milo.server.base.milestone.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class MilestoneMediaPredicateBuilder
    extends PredicateBuilder<QMilestoneMediaEntity, MilestoneMediaEntity> {

  public MilestoneMediaPredicateBuilder() {
    super(QMilestoneMediaEntity.milestoneMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
