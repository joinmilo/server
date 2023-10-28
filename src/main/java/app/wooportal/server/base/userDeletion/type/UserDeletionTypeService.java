package app.wooportal.server.base.userDeletion.type;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UserDeletionTypeService extends DataService<UserDeletionTypeEntity, UserDeletionTypePredicateBuilder> {

  public UserDeletionTypeService(DataRepository<UserDeletionTypeEntity> repo,
      UserDeletionTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
