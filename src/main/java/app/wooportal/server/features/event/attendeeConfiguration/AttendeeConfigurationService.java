package app.wooportal.server.features.event.attendeeConfiguration;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AttendeeConfigurationService extends DataService<AttendeeConfigurationEntity, AttendeeConfigurationPredicateBuilder> {

  public AttendeeConfigurationService(DataRepository<AttendeeConfigurationEntity> repo, AttendeeConfigurationPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
