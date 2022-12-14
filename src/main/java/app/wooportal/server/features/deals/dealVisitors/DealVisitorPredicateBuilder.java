package app.wooportal.server.features.deals.dealVisitors;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.features.deals.category.QDealCategoryEntity;

@Service
public class DealVisitorPredicateBuilder
    extends PredicateBuilder<QDealCategoryEntity, DealVisitorEntity> {

  public DealVisitorPredicateBuilder() {
    super(QDealCategoryEntity.dealCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.icon.likeIgnoreCase(term).or(query.color.likeIgnoreCase(term));
  }
}
