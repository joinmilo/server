package app.wooportal.server.base.cms.menues;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class MenuPredicateBuilder extends PredicateBuilder<QMenuEntity, MenuEntity> {

  public MenuPredicateBuilder() {
    super(QMenuEntity.menuEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatable.any().name.likeIgnoreCase(term);
  }
}
