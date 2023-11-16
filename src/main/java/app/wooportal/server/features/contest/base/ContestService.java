package app.wooportal.server.features.contest.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.contact.ContactService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.contest.base.media.ContestMediaService;


@Service
public class ContestService extends DataService<ContestEntity, ContestPredicateBuilder> {

  public ContestService(DataRepository<ContestEntity> repo, ContestPredicateBuilder predicate,
      ContactService contactService, ContestMediaService uploads) {
    super(repo, predicate);
    
    addService("uploads", uploads);
    addService("contact", contactService);
  }
  
  public void preCreate(ContestEntity entity, ContestEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
  }
  
  public Boolean sponsorContest(String contestId) {
    var contest = getById(contestId);
    
    if (contest.isPresent()) {
      contest.get().setSponsored(true);
      repo.save(contest.get());
      
      unsponsorOther(contestId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOther(String contestId) {
    var others = readAll(
        collectionQuery(predicate.withoutId(contestId))
          .and(predicate.withSponsoredTrue()));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(contest -> {
        contest.setSponsored(false);
        repo.save(contest);
      });
    }
  }
}
