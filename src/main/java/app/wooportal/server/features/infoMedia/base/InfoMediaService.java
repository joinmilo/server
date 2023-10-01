package app.wooportal.server.features.infoMedia.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class InfoMediaService extends DataService<InfoMediaEntity, InfoMediaPredicateBuilder> {

  public InfoMediaService(
      DataRepository<InfoMediaEntity> repo,
      InfoMediaPredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);

  }
}
