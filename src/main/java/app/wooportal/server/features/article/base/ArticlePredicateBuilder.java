package app.wooportal.server.features.article.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

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
        .or(query.seoDescription.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term));
  }
}
