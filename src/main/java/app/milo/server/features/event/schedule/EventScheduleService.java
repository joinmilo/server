package app.milo.server.features.event.schedule;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.utils.SortPageUtils;

@Service
public class EventScheduleService extends DataService<EventScheduleEntity, EventSchedulePredicateBuilder> {

  public EventScheduleService(DataRepository<EventScheduleEntity> repo, EventSchedulePredicateBuilder predicate) {
    super(repo, predicate);
  }

  public Optional<EventScheduleEntity> getByEventAndBetween(
      String eventId,
      OffsetDateTime begin,
      OffsetDateTime end) {
    var result = repo.findAll(collectionQuery(predicate.withEventId(eventId))
        .and(predicate.startDateBetween(begin, end)));
    
    return result != null && !result.isEmpty() 
        ? Optional.ofNullable(result.get(0)) 
        : Optional.empty();
  }

  public Optional<EventScheduleEntity> getMostRecentByEvent(String eventId) {
    var result = repo.findAll(
        collectionQuery(predicate.withEventId(eventId))
          .and(predicate.withStartDateLaterThanToday())
          .setLimit(1)
          .setSort(SortPageUtils.createSort(Direction.ASC, "startDate")));
    
    return result != null && !result.isEmpty()
        ? Optional.ofNullable(result.get(0))
        : Optional.empty();
  }

  public Boolean hasSchedules(String eventId) {
    return repo.exists(predicate.withEventId(eventId));
  }
}
