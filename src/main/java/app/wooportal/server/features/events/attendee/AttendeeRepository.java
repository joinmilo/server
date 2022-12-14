package app.wooportal.server.features.events.attendee;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends DataRepository<AttendeeEntity> {

}
