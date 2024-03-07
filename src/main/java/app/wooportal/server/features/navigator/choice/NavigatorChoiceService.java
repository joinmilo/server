package app.wooportal.server.features.navigator.choice;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class NavigatorChoiceService
    extends DataService<NavigatorChoiceEntity, NavigatorChoicePredicateBuilder> {

  public NavigatorChoiceService(DataRepository<NavigatorChoiceEntity> repo,
      NavigatorChoicePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
