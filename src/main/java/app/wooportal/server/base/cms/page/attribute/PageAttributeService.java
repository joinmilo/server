package app.wooportal.server.base.cms.page.attribute;

import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.page.attributeReference.PageAttributeReferenceService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageAttributeService
    extends DataService<PageAttributeEntity, PageAttributePredicateBuilder> {

  public PageAttributeService(
      DataRepository<PageAttributeEntity> repo,
      PageAttributePredicateBuilder predicate,
      PageAttributeReferenceService referenceService) {
    super(repo, predicate);
    
    addService("references", referenceService);
  }
}
