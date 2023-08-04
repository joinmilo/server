package app.wooportal.server.features.infoMedia.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class InfoMediaService extends DataService<InfoMediaEntity, InfoMediaPredicateBuilder> {

  public InfoMediaService(
      DataRepository<InfoMediaEntity> repo,
      InfoMediaPredicateBuilder predicate) {
    super(repo, predicate);

  }
}
