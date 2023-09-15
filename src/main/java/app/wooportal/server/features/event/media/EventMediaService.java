package app.wooportal.server.features.event.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventMediaService
    extends DataService<EventMediaEntity, EventMediaPredicateBuilder> {

  public EventMediaService(DataRepository<EventMediaEntity> repo,
      EventMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
