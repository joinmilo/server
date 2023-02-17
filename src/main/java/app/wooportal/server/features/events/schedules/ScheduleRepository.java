package app.wooportal.server.features.events.schedules;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ScheduleRepository extends DataRepository<ScheduleEntity> {

}
