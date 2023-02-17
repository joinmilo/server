package app.wooportal.server.features.articles.comments;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ArticleCommentPredicateBuilder
    extends PredicateBuilder<QArticleCommentEntity, ArticleCommentEntity> {

  public ArticleCommentPredicateBuilder() {
    super(QArticleCommentEntity.articleCommentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
