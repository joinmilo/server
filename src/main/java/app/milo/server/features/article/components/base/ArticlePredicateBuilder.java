package app.milo.server.features.article.components.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ArticlePredicateBuilder extends PredicateBuilder<QArticleEntity, ArticleEntity> {

  public ArticlePredicateBuilder() {
    super(QArticleEntity.articleEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {    
    return query.translatables.any().name.likeIgnoreCase(term)
        .or(query.author.user.firstName.likeIgnoreCase(term))
        .or(query.author.user.lastName.likeIgnoreCase(term));
  }

  public BooleanExpression withoutId(String articleId) {
    return query.id.ne(articleId);
  }

  public BooleanExpression withAuthor(String authorId) {
    return query.author.id.eq(authorId);
  }
  
  public BooleanExpression withSponsoredTrue() {
    return query.sponsored.isTrue();
  }
}
