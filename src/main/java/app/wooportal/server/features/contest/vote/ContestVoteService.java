package app.wooportal.server.features.contest.vote;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.userContext.security.UserContextAuthorizationService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestVoteService
    extends DataService<ContestVoteEntity, ContestVotePredicateBuilder> {

  private final UserContextAuthorizationService authService;

  public ContestVoteService(DataRepository<ContestVoteEntity> repo,
      ContestVotePredicateBuilder predicate, UserContextAuthorizationService authService) {
    super(repo, predicate);

    this.authService = authService;
  }

  @Override
  public void preCreate(ContestVoteEntity entity, ContestVoteEntity newEntity, JsonNode context) {

    var currentUser = authService.getAuthenticatedUserContext();

    if (currentUser.isPresent()) {
      newEntity.setUserContext(currentUser.get());
      addContext("userContext", context);
    } 
  }
}
