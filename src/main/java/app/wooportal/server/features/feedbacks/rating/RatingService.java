package app.wooportal.server.features.feedbacks.rating;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class RatingService extends DataService<RatingEntity, RatingPredicateBuilder> {

  public RatingService(DataRepository<RatingEntity> repo, RatingPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
