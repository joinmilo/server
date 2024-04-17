package app.milo.server.features.navigator.resultLink;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;


@Service
public class NavigatorResultLinkService
    extends DataService<NavigatorResultLinkEntity, NavigatorResultLinkPredicateBuilder> {

  public NavigatorResultLinkService(DataRepository<NavigatorResultLinkEntity> repo,
      NavigatorResultLinkPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
