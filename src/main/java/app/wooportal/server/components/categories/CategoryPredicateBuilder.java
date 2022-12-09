package app.wooportal.server.components.categories;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class CategoryPredicateBuilder extends PredicateBuilder<QCategoryEntity, CategoryEntity> {

  public CategoryPredicateBuilder() {
    super(QCategoryEntity.categoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.color.likeIgnoreCase(term).or(query.icon.likeIgnoreCase(term));
  }
}
