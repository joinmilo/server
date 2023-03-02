package app.wooportal.server.base.userContext.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UserContextService extends DataService<UserContextEntity, UserContextPredicateBuilder> {

  public UserContextService(DataRepository<UserContextEntity> repo, UserContextPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
