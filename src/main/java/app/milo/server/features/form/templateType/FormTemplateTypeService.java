package app.milo.server.features.form.templateType;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class FormTemplateTypeService extends DataService<FormTemplateTypeEntity, FormTemplateTypePredicateBuilder> {

  public FormTemplateTypeService(DataRepository<FormTemplateTypeEntity> repo, FormTemplateTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
