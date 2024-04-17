package app.milo.server.features.contest.base.media;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ContestMediaService
    extends DataService<ContestMediaEntity, ContestMediaPredicateBuilder> {

  public ContestMediaService(DataRepository<ContestMediaEntity> repo,
      ContestMediaPredicateBuilder predicate, MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
}
