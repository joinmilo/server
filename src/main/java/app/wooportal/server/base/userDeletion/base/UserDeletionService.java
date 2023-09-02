package app.wooportal.server.base.userDeletion.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UserDeletionService extends DataService<UserDeletionEntity, UserDeletionPredicateBuilder> {

  public UserDeletionService(DataRepository<UserDeletionEntity> repo,
      UserDeletionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}