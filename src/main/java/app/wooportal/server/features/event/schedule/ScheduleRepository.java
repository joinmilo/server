package app.wooportal.server.features.event.schedule;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ScheduleRepository extends DataRepository<ScheduleEntity> {

}
