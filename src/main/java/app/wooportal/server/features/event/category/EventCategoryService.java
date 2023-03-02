package app.wooportal.server.features.event.category;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventCategoryService extends DataService<EventCategoryEntity, EventCategoryPredicateBuilder> {

  public EventCategoryService(DataRepository<EventCategoryEntity> repo, EventCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
