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
    return query.metaDescription.likeIgnoreCase(term)
        .or(query.slug.likeIgnoreCase(term))
        .or(query.contact.name.likeIgnoreCase(term))
        .or(query.contact.email.likeIgnoreCase(term))
        .or(query.translatables.any().shortDescription.likeIgnoreCase(term))
        .or(query.translatables.any().name.likeIgnoreCase(term));
  }
  
  public BooleanExpression withoutId(String dealId) {
    return query.id.ne(dealId);
  }
}
