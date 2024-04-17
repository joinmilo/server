package app.milo.server.base.cms.components.page.attribute;

import org.springframework.stereotype.Service;
import app.milo.server.base.cms.components.page.attributeReference.PageAttributeReferenceService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

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
