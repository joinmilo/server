package app.wooportal.server.base.cms.page.attributeReference;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageAttributeReferencePredicateBuilder
    extends PredicateBuilder<QPageAttributeReferenceEntity, PageAttributeReferenceEntity> {

  public PageAttributeReferencePredicateBuilder() {
    super(QPageAttributeReferenceEntity.pageAttributeReferenceEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.referenceId.likeIgnoreCase(term);
  }

}
