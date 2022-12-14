package app.wooportal.server.features.schedules;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ScheduleService extends DataService<ScheduleEntity, SchedulePredicateBuilder> {

  public ScheduleService(DataRepository<ScheduleEntity> repo, SchedulePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
