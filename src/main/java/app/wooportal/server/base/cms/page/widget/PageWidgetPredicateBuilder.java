package app.wooportal.server.base.cms.page.widget;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageWidgetPredicateBuilder
    extends PredicateBuilder<QPageWidgetEntity, PageWidgetEntity> {

  public PageWidgetPredicateBuilder() {
    super(QPageWidgetEntity.pageWidgetEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.type.translatables.any().name.likeIgnoreCase(term);
  }
}
