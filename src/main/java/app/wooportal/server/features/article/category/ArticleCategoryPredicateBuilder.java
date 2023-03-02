package app.wooportal.server.features.article.category;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ArticleCategoryPredicateBuilder
    extends PredicateBuilder<QArticleCategoryEntity, ArticleCategoryEntity> {

  public ArticleCategoryPredicateBuilder() {
    super(QArticleCategoryEntity.articleCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.icon.likeIgnoreCase(term).or(query.color.likeIgnoreCase(term));
  }
}
