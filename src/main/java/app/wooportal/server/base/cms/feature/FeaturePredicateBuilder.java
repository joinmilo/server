package app.wooportal.server.base.cms.feature;

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
    return query.keyword.likeIgnoreCase(term).or(query.translatables.any().name.likeIgnoreCase(term));
  }
  public BooleanExpression withKeyword(String keyword) {
    return keyword != null && !keyword.isBlank() ? query.keyword.equalsIgnoreCase(keyword)
        : null;
  }
}
