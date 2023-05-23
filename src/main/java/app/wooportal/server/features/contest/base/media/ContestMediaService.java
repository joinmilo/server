package app.wooportal.server.features.contest.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestMediaService
    extends DataService<ContestMediaEntity, ContestMediaPredicateBuilder> {

  public ContestMediaService(DataRepository<ContestMediaEntity> repo,
      ContestMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
