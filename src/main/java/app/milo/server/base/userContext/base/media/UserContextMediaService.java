package app.milo.server.base.userContext.base.media;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;

@Service
public class UserContextMediaService
    extends DataService<UserContextMediaEntity, UserContextMediaPredicateBuilder> {

  public UserContextMediaService(DataRepository<UserContextMediaEntity> repo,
      UserContextMediaPredicateBuilder predicate, MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
  
  @Override
  public Optional<UserContextMediaEntity> getExisting(UserContextMediaEntity entity) {
    return entity != null
        && entity.getMedia() != null
        && entity.getMedia().getId() != null
        && entity.getUserContext() != null
        && entity.getUserContext().getId() != null
       ? repo.findOne(singleQuery(predicate.withMedia(entity.getMedia().getId()))
           .and(predicate.withUserContext(entity.getUserContext().getId()))
         )
       : Optional.empty();
  }
}
