package app.milo.server.base.cms.components.themeVariable;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ThemeVariableService
    extends DataService<ThemeVariableEntity, ThemeVariablePredicateBuilder> {

  public ThemeVariableService(DataRepository<ThemeVariableEntity> repo,
      ThemeVariablePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
