package app.milo.server.features.deal.components.category;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class DealCategoryPredicateBuilder
    extends PredicateBuilder<QDealCategoryEntity, DealCategoryEntity> {

  public DealCategoryPredicateBuilder() {
    super(QDealCategoryEntity.dealCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.icon.likeIgnoreCase(term).or(query.color.likeIgnoreCase(term));
  }
}
