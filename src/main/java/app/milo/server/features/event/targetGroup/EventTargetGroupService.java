package app.milo.server.features.event.targetGroup;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class EventTargetGroupService extends DataService<EventTargetGroupEntity, EventTargetGroupPredicateBuilder> {

  public EventTargetGroupService(DataRepository<EventTargetGroupEntity> repo, EventTargetGroupPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
