package app.wooportal.server.features.navigator.resultPage;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class NavigatorResultPageService
    extends DataService<NavigatorResultPageEntity, NavigatorResultPagePredicateBuilder> {

  public NavigatorResultPageService(DataRepository<NavigatorResultPageEntity> repo,
      NavigatorResultPagePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
