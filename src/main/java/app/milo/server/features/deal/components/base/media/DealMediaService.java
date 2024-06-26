package app.milo.server.features.deal.components.base.media;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;

@Service
public class DealMediaService
    extends DataService<DealMediaEntity, DealMediaPredicateBuilder> {

  public DealMediaService(DataRepository<DealMediaEntity> repo,
      DealMediaPredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
  
  @Override
  public Optional<DealMediaEntity> getExisting(DealMediaEntity entity) {
    return entity != null
        && entity.getMedia() != null
        && entity.getMedia().getId() != null
        && entity.getDeal() != null
        && entity.getDeal().getId() != null
       ? repo.findOne(singleQuery(predicate.withMedia(entity.getMedia().getId()))
           .and(predicate.withDeal(entity.getDeal().getId()))
         )
       : Optional.empty();
  }
}
