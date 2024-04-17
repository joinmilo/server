package app.milo.server.base.cms.components.page.embedding;

import org.springframework.stereotype.Service;
import app.milo.server.base.cms.components.page.attribute.PageAttributeService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class PageEmbeddingService
    extends DataService<PageEmbeddingEntity, PageEmbeddingPredicateBuilder> {

  public PageEmbeddingService(
      DataRepository<PageEmbeddingEntity> repo,
      PageEmbeddingPredicateBuilder predicate,
      PageAttributeService attributeService) {
    super(repo, predicate);
    
    addService("attributes", attributeService);
  }
}
