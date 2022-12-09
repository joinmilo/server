package app.wooportal.server.components.feedbacks.rating;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class RatingPredicateBuilder extends PredicateBuilder<QRatingEntity, RatingEntity> {

  public RatingPredicateBuilder() {
    super(QRatingEntity.ratingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.user.id.likeIgnoreCase(term);
  }
}
