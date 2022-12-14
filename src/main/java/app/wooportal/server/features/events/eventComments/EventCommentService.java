package app.wooportal.server.features.events.eventComments;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventCommentService extends DataService<EventCommentEntity, EventCommentPredicateBuilder> {

  public EventCommentService(DataRepository<EventCommentEntity> repo, EventCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
