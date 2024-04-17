package app.milo.server.features.event.comment;

import java.util.Optional;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.utils.SortPageUtils;

@Service
public class EventCommentService extends DataService<EventCommentEntity, EventCommentPredicateBuilder> {

  public EventCommentService(DataRepository<EventCommentEntity> repo, EventCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }

  public Optional<EventCommentEntity> getMostRecentByEvent(String eventId) {
    var result = repo.findAll(
        collectionQuery(predicate.withEventId(eventId))
          .setLimit(1)
          .setSort(SortPageUtils.createSort(Direction.DESC, "modified")));
    
    return result != null && !result.isEmpty()
        ? Optional.ofNullable(result.get(0))
        : Optional.empty();
  }
}
