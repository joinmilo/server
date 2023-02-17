package app.wooportal.server.features.forms.template;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class FormTemplatePredicateBuilder
    extends PredicateBuilder<QFormTemplateEntity, FormTemplateEntity> {

  public FormTemplatePredicateBuilder() {
    super(QFormTemplateEntity.formTemplateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().content.likeIgnoreCase(term);
  }
}
