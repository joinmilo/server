package app.milo.server.features.contest.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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
  
  public BooleanExpression withoutId(String contestId) {
    return query.id.ne(contestId);
  }

  public BooleanExpression withSponsoredTrue() {
    return query.sponsored.isTrue();
  }
}
