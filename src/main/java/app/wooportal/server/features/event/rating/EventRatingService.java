package app.wooportal.server.features.event.rating;

import org.springframework.stereotype.Service;
import app.wooportal.server.base.rating.RatingDto;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.event.base.EventEntity;

@Service
public class EventRatingService extends DataService<EventRatingEntity, EventRatingPredicateBuilder> {

  public EventRatingService(DataRepository<EventRatingEntity> repo, EventRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  public RatingDto calculateRatings(EventEntity event){
    return null;
  }
}
