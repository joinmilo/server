package app.milo.server.features.event.attendeeConfiguration;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface EventAttendeeConfigurationRepository extends DataRepository<EventAttendeeConfigurationEntity> {

}
