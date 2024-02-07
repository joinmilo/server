package app.wooportal.server.features.contest.vote;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.userContext.security.UserContextAuthorizationService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.contest.base.ContestService;

@Service
public class ContestVoteService
    extends DataService<ContestVoteEntity, ContestVotePredicateBuilder> {

  private final UserContextAuthorizationService authService;

  private final ContestService contestService;

  public ContestVoteService(DataRepository<ContestVoteEntity> repo,
      ContestVotePredicateBuilder predicate, UserContextAuthorizationService authService,
      ContestService contestService) {
    super(repo, predicate);

    this.authService = authService;
    this.contestService = contestService;
  }

  @Override
  public void preCreate(ContestVoteEntity entity, ContestVoteEntity newEntity, JsonNode context) {

    var currentUser = authService.getAuthenticatedUserContext();
    if (currentUser.isPresent()) {
      newEntity.setUserContext(currentUser.get());
      addContext("userContext", context);

      var contest =
          contestService.getById(newEntity.getContestParticipation().getContest().getId()).get();

      if (contest.getMaxVotes() == null
          || contest.getMaxVotes() > readAll(collectionQuery(predicate.withContest(contest.getId())
              .and(predicate.withUserContext(currentUser.get().getId())))).getTotal()) {
      } else {
        throw new BadParamsException("Maxium participations reached", null);
      }
    }
  }
}
