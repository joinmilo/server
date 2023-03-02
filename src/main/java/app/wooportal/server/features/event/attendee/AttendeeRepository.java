package app.wooportal.server.features.event.attendee;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface AttendeeRepository extends DataRepository<AttendeeEntity> {

}
