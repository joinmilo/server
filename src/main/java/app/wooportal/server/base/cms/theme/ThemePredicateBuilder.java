package app.wooportal.server.base.cms.theme;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ThemePredicateBuilder
    extends PredicateBuilder<QThemeEntity, ThemeEntity> {

  public ThemePredicateBuilder() {
    super(QThemeEntity.themeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
