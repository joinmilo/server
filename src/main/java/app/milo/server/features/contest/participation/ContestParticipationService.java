package app.milo.server.features.contest.participation;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.userContext.security.UserContextAuthorizationService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.error.exception.BadParamsException;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.features.contest.base.ContestService;
import app.milo.server.features.contest.participation.media.ContestParticipationMediaService;

@Service
public class ContestParticipationService
    extends DataService<ContestParticipationEntity, ContestParticipationPredicateBuilder> {


  private final UserContextAuthorizationService authService;
  private final ContestService contestService;

  public ContestParticipationService(DataRepository<ContestParticipationEntity> repo,
      ContestParticipationPredicateBuilder predicate, ContestParticipationMediaService mediaService,
      UserContextAuthorizationService authService, ContestService contestService) {
    super(repo, predicate);

    this.authService = authService;
    this.contestService = contestService;
    addService("mediaSubmissions", mediaService);

  }

  @Override
  public void preCreate(ContestParticipationEntity entity, ContestParticipationEntity newEntity,
      JsonNode context) {
    var currentUser = authService.getAuthenticatedUserContext();

    if (currentUser.isPresent()) {
      newEntity.setUserContext(currentUser.get());
      addContext("userContext", context);

      var contest = contestService.getById(newEntity.getContest().getId()).get(); 
      if (contest.getMaxParticipations() == null ||
          contest.getMaxParticipations() > readAll(collectionQuery(
          predicate.withContest(contest.getId())
          .and(predicate.withUserContext(currentUser.get().getId())))).getTotal()) {
        
        newEntity.setApproved(false);
        addContext("approved", context);

        if (!this.contestService.getById(newEntity.getContest().getId()).get()
            .getParticipationApproval()) {
          newEntity.setApproved(true);
          addContext("approved", context);
        }
      } else {
        throw new BadParamsException("Maxium participations reached", null);
      }
    }
  }
}
