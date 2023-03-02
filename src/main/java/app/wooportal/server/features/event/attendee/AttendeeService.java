package app.wooportal.server.features.event.attendee;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AttendeeService extends DataService<AttendeeEntity, AttendeePredicateBuilder> {

  public AttendeeService(DataRepository<AttendeeEntity> repo, AttendeePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
