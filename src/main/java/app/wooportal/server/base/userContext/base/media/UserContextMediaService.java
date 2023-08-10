package app.wooportal.server.base.userContext.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UserContextMediaService
    extends DataService<UserContextMediaEntity, UserContextMediaPredicateBuilder> {

  public UserContextMediaService(DataRepository<UserContextMediaEntity> repo,
      UserContextMediaPredicateBuilder predicate, MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
}
