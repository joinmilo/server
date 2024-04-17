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
    return  query.publicAuthor.name.likeIgnoreCase(term)
        .or(query.author.id.likeIgnoreCase(term))
        .or(query.publicAuthor.phone.likeIgnoreCase(term))
        .or(query.metaDescription.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term));
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
