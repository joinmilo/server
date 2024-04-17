package app.milo.server.core.i18n.components.language;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class LanguagePredicateBuilder extends PredicateBuilder<QLanguageEntity, LanguageEntity> {

  public LanguagePredicateBuilder() {
    super(QLanguageEntity.languageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.locale.likeIgnoreCase(term)
        .or(query.name.likeIgnoreCase(term));
  }
  
  public BooleanExpression withActive() {
    return query.active.isTrue();
  }
  
  public BooleanExpression withLocale(String locale) {
    return query.locale.eq(locale);
  }

  public BooleanExpression withoutLocale(String locale) {
    return locale != null && !locale.isBlank()
        ? query.locale.ne(locale)
        : null;
  }

}
