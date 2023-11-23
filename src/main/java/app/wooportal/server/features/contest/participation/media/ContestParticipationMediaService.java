package app.wooportal.server.features.contest.participation.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestParticipationMediaService
    extends DataService<ContestParticipationMediaEntity, ContestParticipationMediaPredicateBuilder> {

  public ContestParticipationMediaService(DataRepository<ContestParticipationMediaEntity> repo,
      ContestParticipationMediaPredicateBuilder predicate, MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
}
