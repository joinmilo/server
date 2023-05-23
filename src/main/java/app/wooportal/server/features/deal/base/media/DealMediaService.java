package app.wooportal.server.features.deal.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class DealMediaService
    extends DataService<DealMediaEntity, DealMediaPredicateBuilder> {

  public DealMediaService(DataRepository<DealMediaEntity> repo,
      DealMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
