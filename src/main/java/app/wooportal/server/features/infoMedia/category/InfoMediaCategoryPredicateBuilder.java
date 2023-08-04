package app.wooportal.server.features.infoMedia.category;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class InfoMediaCategoryPredicateBuilder
    extends PredicateBuilder<QInfoMediaCategoryEntity, InfoMediaCategoryEntity> {

  public InfoMediaCategoryPredicateBuilder() {
    super(QInfoMediaCategoryEntity.infoMediaCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
