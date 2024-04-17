package app.milo.server.features.navigator.choice;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;


@Service
public class NavigatorChoiceService
    extends DataService<NavigatorChoiceEntity, NavigatorChoicePredicateBuilder> {

  public NavigatorChoiceService(DataRepository<NavigatorChoiceEntity> repo,
      NavigatorChoicePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
