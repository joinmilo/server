package app.wooportal.server.base.cms.menues;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MenuService
    extends DataService<MenuEntity, MenuPredicateBuilder> {

  public MenuService(DataRepository<MenuEntity> repo,
      MenuPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
