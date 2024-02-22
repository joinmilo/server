package app.wooportal.server.features.navigator.connections;


import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class NavigatorConnectionService extends DataService<NavigatorConnectionEntity, NavigatorConnectionPredicateBuilder> {

  public NavigatorConnectionService(DataRepository<NavigatorConnectionEntity> repo,
      NavigatorConnectionPredicateBuilder predicate) {
    super(repo, predicate);
  
    
  }
}
