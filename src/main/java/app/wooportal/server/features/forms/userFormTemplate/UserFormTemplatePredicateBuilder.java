package app.wooportal.server.features.forms.userFormTemplate;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class UserFormTemplatePredicateBuilder
    extends PredicateBuilder<QUserFormTemplateEntity, UserFormTemplateEntity> {

  public UserFormTemplatePredicateBuilder() {
    super(QUserFormTemplateEntity.userFormTemplateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
