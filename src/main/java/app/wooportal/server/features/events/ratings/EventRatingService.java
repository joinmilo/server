package app.wooportal.server.features.events.ratings;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventRatingService extends DataService<EventRatingEntity, EventRatingPredicateBuilder> {

  public EventRatingService(DataRepository<EventRatingEntity> repo, EventRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
