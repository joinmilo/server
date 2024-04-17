package app.milo.server.base.milestone.element;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class MilestoneElementService extends DataService<MilestoneElementEntity, MilestoneElementPredicateBuilder> {

  public MilestoneElementService(DataRepository<MilestoneElementEntity> repo, MilestoneElementPredicateBuilder predicate) {
    super(repo, predicate);
  }
}


