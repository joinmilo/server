package app.wooportal.server.core.media.attribution;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MediaAttributionService extends DataService<MediaAttributionEntity, MediaAttributionPredicateBuilder> {

  public MediaAttributionService(
      DataRepository<MediaAttributionEntity> repo,
      MediaAttributionPredicateBuilder predicate) {
    super(repo, predicate);
  }

}

