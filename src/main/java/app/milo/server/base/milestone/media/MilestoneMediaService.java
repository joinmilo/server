package app.milo.server.base.milestone.media;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class MilestoneMediaService
    extends DataService<MilestoneMediaEntity, MilestoneMediaPredicateBuilder> {

  public MilestoneMediaService(DataRepository<MilestoneMediaEntity> repo,
      MilestoneMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
