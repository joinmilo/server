package app.wooportal.server.base.cms.landings;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class LandingPredicateBuilder extends PredicateBuilder<QLandingEntity, LandingEntity> {

  public LandingPredicateBuilder() {
    super(QLandingEntity.landingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.url.likeIgnoreCase(term)
        .or(query.translatable.any().shortDescription.likeIgnoreCase(term));
  }
}
