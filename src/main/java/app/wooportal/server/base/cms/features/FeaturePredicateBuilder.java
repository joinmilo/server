package app.wooportal.server.base.cms.features;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class FeaturePredicateBuilder extends PredicateBuilder<QFeatureEntity, FeatureEntity> {

  public FeaturePredicateBuilder() {
    super(QFeatureEntity.featureEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term).or(query.translatables.any().name.likeIgnoreCase(term));
  }
}
