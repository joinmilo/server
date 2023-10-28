package app.wooportal.server.base.milestone;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MilestoneService extends DataService<MilestoneEntity, MilestonePredicateBuilder> {

  public MilestoneService(DataRepository<MilestoneEntity> repo, MilestonePredicateBuilder predicate) {
    super(repo, predicate);
  }
}


