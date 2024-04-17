package app.milo.server.features.event.rating;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.features.event.base.EventEntity;

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
  
  @Override
  public Optional<EventRatingEntity> getExisting(EventRatingEntity entity) {
    return entity != null
        && entity.getUserContext() != null
        && entity.getUserContext().getId() != null
        && entity.getParent() != null
        && entity.getParent().getId() != null
       ? repo.findOne(singleQuery(predicate.withUserContext(entity.getUserContext().getId()))
           .and(predicate.withParentId(entity.getParent().getId()))
         )
       : Optional.empty();
  }
  
}
