package app.milo.server.base.milestone;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class MilestoneService extends DataService<MilestoneEntity, MilestonePredicateBuilder> {

  public MilestoneService(DataRepository<MilestoneEntity> repo, MilestonePredicateBuilder predicate) {
    super(repo, predicate);
  }
}


