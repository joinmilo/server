package app.wooportal.server.features.forms.templateType;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class FormTemplateTypeService extends DataService<FormTemplateTypeEntity, FormTemplateTypePredicateBuilder> {

  public FormTemplateTypeService(DataRepository<FormTemplateTypeEntity> repo, FormTemplateTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
