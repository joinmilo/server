package app.wooportal.server.base.cms.theme;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ThemeService
    extends DataService<ThemeEntity, ThemePredicateBuilder> {

  public ThemeService(DataRepository<ThemeEntity> repo,
      ThemePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
