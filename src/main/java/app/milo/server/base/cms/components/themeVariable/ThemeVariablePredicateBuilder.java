package app.milo.server.base.cms.components.themeVariable;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ThemeVariablePredicateBuilder
    extends PredicateBuilder<QThemeVariableEntity, ThemeVariableEntity> {

  public ThemeVariablePredicateBuilder() {
    super(QThemeVariableEntity.themeVariableEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.code.likeIgnoreCase(term);
  }
}
