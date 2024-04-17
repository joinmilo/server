package app.milo.server.features.form.template;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class FormTemplateService extends DataService<FormTemplateEntity, FormTemplatePredicateBuilder> {

  public FormTemplateService(DataRepository<FormTemplateEntity> repo, FormTemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
