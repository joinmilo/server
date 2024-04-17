package app.milo.server.features.contest.participation.media;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ContestParticipationMediaService
    extends DataService<ContestParticipationMediaEntity, ContestParticipationMediaPredicateBuilder> {

  public ContestParticipationMediaService(DataRepository<ContestParticipationMediaEntity> repo,
      ContestParticipationMediaPredicateBuilder predicate, MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
}
