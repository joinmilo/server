package app.wooportal.server.base.cms.components.page.attributeReference;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

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
