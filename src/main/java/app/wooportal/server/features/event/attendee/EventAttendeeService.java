package app.wooportal.server.features.event.attendee;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventAttendeeService extends DataService<EventAttendeeEntity, EventAttendeePredicateBuilder> {

  public EventAttendeeService(DataRepository<EventAttendeeEntity> repo, EventAttendeePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
