package app.wooportal.server.features.event.attendeeConfiguration;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventAttendeeConfigurationService extends DataService<EventAttendeeConfigurationEntity, EventAttendeeConfigurationPredicateBuilder> {

  public EventAttendeeConfigurationService(DataRepository<EventAttendeeConfigurationEntity> repo, EventAttendeeConfigurationPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
