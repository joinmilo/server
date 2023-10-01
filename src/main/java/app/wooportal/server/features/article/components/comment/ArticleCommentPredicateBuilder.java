package app.wooportal.server.features.article.components.comment;

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
  
  public BooleanExpression withArticleId(String articleId) {
    return query.article.id.eq(articleId);
  }
}
