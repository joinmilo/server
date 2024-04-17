package app.milo.server.features.form.templateType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class FormTemplateTypePredicateBuilder
    extends PredicateBuilder<QFormTemplateTypeEntity, FormTemplateTypeEntity> {

  public FormTemplateTypePredicateBuilder() {
    super(QFormTemplateTypeEntity.formTemplateTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }
}
