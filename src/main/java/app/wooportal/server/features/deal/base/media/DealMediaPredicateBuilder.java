package app.wooportal.server.features.deal.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class DealMediaPredicateBuilder
    extends PredicateBuilder<QDealMediaEntity, DealMediaEntity> {

  public DealMediaPredicateBuilder() {
    super(QDealMediaEntity.dealMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
