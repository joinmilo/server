package app.milo.server.features.contest.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.contact.ContactService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.features.contest.base.media.ContestMediaService;


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

    
    if (newEntity.getParticipationApproval() == null) {
      newEntity.setParticipationApproval(false);
      addContext("participationApproval", context);
    }
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
