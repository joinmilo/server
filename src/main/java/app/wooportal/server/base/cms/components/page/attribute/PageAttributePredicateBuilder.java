package app.wooportal.server.base.cms.components.page.attribute;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageAttributePredicateBuilder
    extends PredicateBuilder<QPageAttributeEntity, PageAttributeEntity> {

  public PageAttributePredicateBuilder() {
    super(QPageAttributeEntity.pageAttributeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().translatable.likeIgnoreCase(term);
  }

}
