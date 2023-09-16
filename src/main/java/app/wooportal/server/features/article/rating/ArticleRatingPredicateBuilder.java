package app.wooportal.server.features.article.rating;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ArticleRatingPredicateBuilder
    extends PredicateBuilder<QArticleRatingEntity, ArticleRatingEntity> {

  public ArticleRatingPredicateBuilder() {
    super(QArticleRatingEntity.articleRatingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.parent.translatables.any().name.likeIgnoreCase(term);
  }
  
  public BooleanExpression withParentId(String parentId) {
    return parentId != null
        ? query.parent.id.eq(parentId)
        : null;
  }

  public BooleanExpression modifiedBetween(OffsetDateTime startDate, OffsetDateTime endDate) {
    return startDate != null && endDate != null
        ? query.modified.between(startDate, endDate)
        : null;
  }
}
