package app.wooportal.server.features.events.eventTargetGroups;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventTargetGroupService extends DataService<EventTargetGroupEntity, EventTargetGroupPredicateBuilder> {

  public EventTargetGroupService(DataRepository<EventTargetGroupEntity> repo, EventTargetGroupPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
