package app.milo.server.features.article.components.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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
  
  public BooleanExpression withArticle(String articleId) {
    return articleId != null
        ? query.article.id.eq(articleId)
        : null;
  }

  public BooleanExpression withMedia(String mediaId) {
    return mediaId != null
        ? query.media.id.eq(mediaId)
        : null;
  }

  public BooleanExpression withAuthor(String authorId) {
    return query.article.author.id.eq(authorId);
  }
}
