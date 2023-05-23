package app.wooportal.server.features.contest.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContestMediaPredicateBuilder
    extends PredicateBuilder<QContestMediaEntity, ContestMediaEntity> {

  public ContestMediaPredicateBuilder() {
    super(QContestMediaEntity.contestMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
