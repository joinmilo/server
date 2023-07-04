package app.wooportal.server.features.event.base;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.event.attendeeConfiguration.AttendeeConfigurationService;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {

  public EventService(DataRepository<EventEntity> repo, EventPredicateBuilder predicate,
      @Lazy AttendeeConfigurationService attendeeConfigurationService) {
    super(repo, predicate);

    addService("attendeeConfiguration", attendeeConfigurationService);
  }
}
