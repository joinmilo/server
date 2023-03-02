package app.wooportal.server.features.article.rating;

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
    return null;
  }
}
