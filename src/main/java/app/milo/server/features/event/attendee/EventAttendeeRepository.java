package app.milo.server.features.event.attendee;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface EventAttendeeRepository extends DataRepository<EventAttendeeEntity> {

}
