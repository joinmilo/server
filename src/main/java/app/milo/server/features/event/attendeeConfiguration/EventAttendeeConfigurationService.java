package app.milo.server.features.event.attendeeConfiguration;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class EventAttendeeConfigurationService extends DataService<EventAttendeeConfigurationEntity, EventAttendeeConfigurationPredicateBuilder> {

  public EventAttendeeConfigurationService(DataRepository<EventAttendeeConfigurationEntity> repo, EventAttendeeConfigurationPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
