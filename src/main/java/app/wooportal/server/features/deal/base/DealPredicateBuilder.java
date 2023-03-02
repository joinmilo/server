package app.wooportal.server.features.deal.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class DealPredicateBuilder extends PredicateBuilder<QDealEntity, DealEntity> {

  public DealPredicateBuilder() {
    super(QDealEntity.dealEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.seoDescription.likeIgnoreCase(term).or(query.slug.likeIgnoreCase(term));
  }
}
