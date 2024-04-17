package app.milo.server.base.userContext.friend;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class FriendService extends DataService<FriendEntity, FriendPredicateBuilder> {

  public FriendService(DataRepository<FriendEntity> repo, FriendPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
