package app.milo.server.features.event.category;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class EventCategoryService extends DataService<EventCategoryEntity, EventCategoryPredicateBuilder> {

  public EventCategoryService(DataRepository<EventCategoryEntity> repo, EventCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
