package app.wooportal.server.base.cms.themeVariable;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ThemeVariableService
    extends DataService<ThemeVariableEntity, ThemeVariablePredicateBuilder> {

  public ThemeVariableService(DataRepository<ThemeVariableEntity> repo,
      ThemeVariablePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
