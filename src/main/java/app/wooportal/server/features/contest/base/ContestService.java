package app.wooportal.server.features.contest.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestService extends DataService<ContestEntity, ContestPredicateBuilder> {

  public ContestService(DataRepository<ContestEntity> repo, ContestPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  public Boolean sponsorContest(String contestId) {
    var contest = getById(contestId);
    
    if (contest.isPresent()) {
      contest.get().setSponsored(true);
      repo.save(contest.get());
      
      unsponsorOthers(contestId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOthers(String contestId) {
    var others = readAll(collectionQuery(predicate.withoutId(contestId)));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(contest -> {
        contest.setSponsored(false);
        repo.save(contest);
      });
    }
  }
}
