package app.wooportal.server.base.cms.page.widgetAttribute;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageWidgetAttributePredicateBuilder
    extends PredicateBuilder<QPageWidgetAttributeEntity, PageWidgetAttributeEntity> {

  public PageWidgetAttributePredicateBuilder() {
    super(QPageWidgetAttributeEntity.pageWidgetAttributeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().content.likeIgnoreCase(term);
  }

}
