package app.milo.server.core.media.attribution;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class MediaAttributionService extends DataService<MediaAttributionEntity, MediaAttributionPredicateBuilder> {

  public MediaAttributionService(
      DataRepository<MediaAttributionEntity> repo,
      MediaAttributionPredicateBuilder predicate) {
    super(repo, predicate);
  }

}

