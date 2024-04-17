package app.milo.server.core.security.components.userDeletion.type;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class UserDeletionTypeService extends DataService<UserDeletionTypeEntity, UserDeletionTypePredicateBuilder> {

  public UserDeletionTypeService(DataRepository<UserDeletionTypeEntity> repo,
      UserDeletionTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
