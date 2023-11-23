package app.wooportal.server.features.contest.participation;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.contest.participation.media.ContestParticipationMediaService;

@Service
public class ContestParticipationService
    extends DataService<ContestParticipationEntity, ContestParticipationPredicateBuilder> {

  public ContestParticipationService(DataRepository<ContestParticipationEntity> repo,
      ContestParticipationPredicateBuilder predicate, ContestParticipationMediaService mediaService) {
    super(repo, predicate);
    
    addService("mediaSubmissions", mediaService);
  }
}
