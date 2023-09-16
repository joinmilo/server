package app.wooportal.server.features.event.rating;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.event.base.EventEntity;

@Service
public class EventRatingService extends DataService<EventRatingEntity, EventRatingPredicateBuilder> {

  public EventRatingService(
      DataRepository<EventRatingEntity> repo,
      EventRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }

  public List<EventRatingEntity> getAllBetween(
      EventEntity parent,
      OffsetDateTime startDate,
      OffsetDateTime endDate) {
    return parent != null
        ? repo.findAll(collectionQuery(predicate.withParentId(parent.getId()))
            .and(predicate.modifiedBetween(startDate, endDate))
          ).getList()
        : List.of();
  }
  
}
