package app.milo.server.features.deal.components.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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
  
  public BooleanExpression withMedia(String mediaId) {
    return mediaId != null
        ? query.media.id.eq(mediaId)
        : null;
  }

  public BooleanExpression withDeal(String dealId) {
    return query.deal.id.eq(dealId);
  }
  
  public BooleanExpression withCreator(String creatorId) {
    return query.deal.creator.id.eq(creatorId);
  }
}
