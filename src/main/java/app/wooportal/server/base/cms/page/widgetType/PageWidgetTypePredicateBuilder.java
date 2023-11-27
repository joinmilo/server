package app.wooportal.server.base.cms.page.widgetType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageWidgetTypePredicateBuilder
    extends PredicateBuilder<QPageWidgetTypeEntity, PageWidgetTypeEntity> {

  public PageWidgetTypePredicateBuilder() {
    super(QPageWidgetTypeEntity.pageWidgetTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }
}
