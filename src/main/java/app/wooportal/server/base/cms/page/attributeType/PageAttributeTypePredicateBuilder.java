package app.wooportal.server.base.cms.page.attributeType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageAttributeTypePredicateBuilder
    extends PredicateBuilder<QPageAttributeTypeEntity, PageAttributeTypeEntity> {

  public PageAttributeTypePredicateBuilder() {
    super(QPageAttributeTypeEntity.pageAttributeTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.code.likeIgnoreCase(term);
  }
}
