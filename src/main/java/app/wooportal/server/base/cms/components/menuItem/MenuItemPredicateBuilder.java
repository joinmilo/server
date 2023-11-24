package app.wooportal.server.base.cms.components.menuItem;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class MenuItemPredicateBuilder extends PredicateBuilder<QMenuItemEntity, MenuItemEntity> {

  public MenuItemPredicateBuilder() {
    super(QMenuItemEntity.menuItemEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }
}
