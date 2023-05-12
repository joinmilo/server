package app.wooportal.server.features.contest.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContestPredicateBuilder extends PredicateBuilder<QContestEntity, ContestEntity> {

  public ContestPredicateBuilder() {
    super(QContestEntity.contestEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.type.translatables.any().name.likeIgnoreCase(term)
        .or(query.translatables.any().name.likeIgnoreCase(term))
        .or(query.translatables.any().content.likeIgnoreCase(term))
        .or(query.metaDescription.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term));
  }
}
