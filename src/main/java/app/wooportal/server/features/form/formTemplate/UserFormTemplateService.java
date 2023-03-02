package app.wooportal.server.features.form.formTemplate;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UserFormTemplateService extends DataService<UserFormTemplateEntity, UserFormTemplatePredicateBuilder> {

  public UserFormTemplateService(DataRepository<UserFormTemplateEntity> repo, UserFormTemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
