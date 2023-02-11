package app.wooportal.server.base.cms.pageFeatures;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageFeaturePredicateBuilder
    extends PredicateBuilder<QPageFeatureEntity, PageFeatureEntity> {

  public PageFeaturePredicateBuilder() {
    super(QPageFeatureEntity.pageFeatureEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
