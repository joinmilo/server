package app.wooportal.server.base.milestone.element;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MilestoneElementService extends DataService<MilestoneElementEntity, MilestoneElementPredicateBuilder> {

  public MilestoneElementService(DataRepository<MilestoneElementEntity> repo, MilestoneElementPredicateBuilder predicate) {
    super(repo, predicate);
  }
}


