package app.wooportal.server.features.navigator.resultLink;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class NavigatorResultLinkService
    extends DataService<NavigatorResultLinkEntity, NavigatorResultLinkPredicateBuilder> {

  public NavigatorResultLinkService(DataRepository<NavigatorResultLinkEntity> repo,
      NavigatorResultLinkPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
