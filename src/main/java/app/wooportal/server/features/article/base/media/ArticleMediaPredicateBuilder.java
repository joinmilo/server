package app.wooportal.server.features.article.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ArticleMediaPredicateBuilder
    extends PredicateBuilder<QArticleMediaEntity, ArticleMediaEntity> {

  public ArticleMediaPredicateBuilder() {
    super(QArticleMediaEntity.articleMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
