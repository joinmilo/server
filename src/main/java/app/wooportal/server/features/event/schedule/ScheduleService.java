package app.wooportal.server.features.event.schedule;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.SortPageUtils;

@Service
public class ScheduleService extends DataService<ScheduleEntity, SchedulePredicateBuilder> {

  public ScheduleService(DataRepository<ScheduleEntity> repo, SchedulePredicateBuilder predicate) {
    super(repo, predicate);
  }

  public Optional<ScheduleEntity> getByEventAndBetween(
      String eventId,
      OffsetDateTime begin,
      OffsetDateTime end) {
    var result = repo.findAll(collectionQuery(predicate.withEventId(eventId))
        .and(predicate.startDateBetween(begin, end)));
    
    return result != null && !result.isEmpty() 
        ? Optional.ofNullable(result.get(0)) 
        : Optional.empty();
  }

  public Optional<ScheduleEntity> getMostRecentByEvent(String eventId) {
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
