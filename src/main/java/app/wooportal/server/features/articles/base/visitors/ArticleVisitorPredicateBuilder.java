package app.wooportal.server.features.articles.base.visitors;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ArticleVisitorPredicateBuilder
    extends PredicateBuilder<QArticleVisitorEntity, ArticleVisitorEntity> {

  public ArticleVisitorPredicateBuilder() {
    super(QArticleVisitorEntity.articleVisitorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
