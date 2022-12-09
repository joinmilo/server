package app.wooportal.server.components.events.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {

  public EventService(DataRepository<EventEntity> repo, EventPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
