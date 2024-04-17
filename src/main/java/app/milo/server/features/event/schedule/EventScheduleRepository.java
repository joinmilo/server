package app.milo.server.features.event.schedule;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface EventScheduleRepository extends DataRepository<EventScheduleEntity> {

}
