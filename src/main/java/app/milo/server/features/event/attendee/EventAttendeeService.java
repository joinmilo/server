package app.milo.server.features.event.attendee;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class EventAttendeeService extends DataService<EventAttendeeEntity, EventAttendeePredicateBuilder> {

  public EventAttendeeService(DataRepository<EventAttendeeEntity> repo, EventAttendeePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
