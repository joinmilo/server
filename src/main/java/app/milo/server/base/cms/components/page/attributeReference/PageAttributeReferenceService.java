package app.milo.server.base.cms.components.page.attributeReference;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;

@Service
public class PageAttributeReferenceService
    extends DataService<PageAttributeReferenceEntity, PageAttributeReferencePredicateBuilder> {

  public PageAttributeReferenceService(
      DataRepository<PageAttributeReferenceEntity> repo,
      PageAttributeReferencePredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
}
