package app.milo.server.base.cms.components.theme;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ThemeService
    extends DataService<ThemeEntity, ThemePredicateBuilder> {

  public ThemeService(DataRepository<ThemeEntity> repo,
      ThemePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
