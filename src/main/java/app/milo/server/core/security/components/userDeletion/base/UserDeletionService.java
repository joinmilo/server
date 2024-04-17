package app.milo.server.core.security.components.userDeletion.base;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class UserDeletionService extends DataService<UserDeletionEntity, UserDeletionPredicateBuilder> {

  public UserDeletionService(DataRepository<UserDeletionEntity> repo,
      UserDeletionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}