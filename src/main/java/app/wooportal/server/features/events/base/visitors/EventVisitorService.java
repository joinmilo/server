package app.wooportal.server.features.events.eventVisitors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventVisitorService extends DataService<EventVisitorEntity, EventVisitorPredicateBuilder> {

  public EventVisitorService(DataRepository<EventVisitorEntity> repo, EventVisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
