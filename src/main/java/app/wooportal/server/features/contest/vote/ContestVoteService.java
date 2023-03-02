package app.wooportal.server.features.contest.vote;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestVoteService
    extends DataService<ContestVoteEntity, ContestVotePredicateBuilder> {

  public ContestVoteService(DataRepository<ContestVoteEntity> repo,
      ContestVotePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
