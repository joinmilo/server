package app.wooportal.server.base.cms.components.page.media;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageMediaService
    extends DataService<PageMediaEntity, PageMediaPredicateBuilder> {

  public PageMediaService(DataRepository<PageMediaEntity> repo,
      PageMediaPredicateBuilder predicate, MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
  
  @Override
  public Optional<PageMediaEntity> getExisting(PageMediaEntity entity) {
    return entity != null
        && entity.getMedia() != null
        && entity.getMedia().getId() != null
        && entity.getPage() != null
        && entity.getPage().getId() != null
       ? repo.findOne(singleQuery(predicate.withMedia(entity.getMedia().getId()))
           .and(predicate.withPage(entity.getPage().getId()))
         )
       : Optional.empty();
  }
}
