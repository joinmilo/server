package app.wooportal.server.core.i18n.components.language;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class LanguagePredicateBuilder extends PredicateBuilder<QLanguageEntity, LanguageEntity> {

  public LanguagePredicateBuilder() {
    super(QLanguageEntity.languageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
