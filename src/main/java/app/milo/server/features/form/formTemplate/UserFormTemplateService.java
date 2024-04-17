package app.milo.server.features.form.formTemplate;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class UserFormTemplateService extends DataService<UserFormTemplateEntity, UserFormTemplatePredicateBuilder> {

  public UserFormTemplateService(DataRepository<UserFormTemplateEntity> repo, UserFormTemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
