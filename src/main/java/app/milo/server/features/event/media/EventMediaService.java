package app.milo.server.features.event.media;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;


@Service
public class EventMediaService
    extends DataService<EventMediaEntity, EventMediaPredicateBuilder> {

  public EventMediaService(DataRepository<EventMediaEntity> repo,
      EventMediaPredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
  
  @Override
  public Optional<EventMediaEntity> getExisting(EventMediaEntity entity) {
    return entity != null
        && entity.getMedia() != null
        && entity.getMedia().getId() != null
        && entity.getEvent() != null
        && entity.getEvent().getId() != null
       ? repo.findOne(singleQuery(predicate.withMedia(entity.getMedia().getId()))
           .and(predicate.withEvent(entity.getEvent().getId()))
         )
       : Optional.empty();
  }
}
