package app.wooportal.server.features.contest.type;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContestTypePredicateBuilder
    extends PredicateBuilder<QContestTypeEntity, ContestTypeEntity> {

  public ContestTypePredicateBuilder() {
    super(QContestTypeEntity.contestTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.keyword.likeIgnoreCase(term).or(query.translatables.any().name.likeIgnoreCase(term));
  }
}
