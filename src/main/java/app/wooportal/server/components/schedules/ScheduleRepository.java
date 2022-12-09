package app.wooportal.server.components.schedules;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends DataRepository<ScheduleEntity> {

}
