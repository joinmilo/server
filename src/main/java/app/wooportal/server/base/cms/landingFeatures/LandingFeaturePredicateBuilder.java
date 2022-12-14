package app.wooportal.server.base.cms.landingFeatures;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class LandingFeaturePredicateBuilder
    extends PredicateBuilder<QLandingFeatureEntity, LandingFeatureEntity> {

  public LandingFeaturePredicateBuilder() {
    super(QLandingFeatureEntity.landingFeatureEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
