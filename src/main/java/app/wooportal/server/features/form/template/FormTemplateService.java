package app.wooportal.server.features.form.template;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class FormTemplateService extends DataService<FormTemplateEntity, FormTemplatePredicateBuilder> {

  public FormTemplateService(DataRepository<FormTemplateEntity> repo, FormTemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
