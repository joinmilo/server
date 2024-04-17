package app.milo.server.base.cms.components.page.attributeReference;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class PageAttributeReferencePredicateBuilder
    extends PredicateBuilder<QPageAttributeReferenceEntity, PageAttributeReferenceEntity> {

  public PageAttributeReferencePredicateBuilder() {
    super(QPageAttributeReferenceEntity.pageAttributeReferenceEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.attribute.translatables.any().translatable.likeIgnoreCase(term);
  }

}
