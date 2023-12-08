package app.wooportal.server.base.cms.page.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.page.base.media.PageMediaService;
import app.wooportal.server.base.cms.page.embedding.PageEmbeddingService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageService
    extends DataService<PageEntity, PagePredicateBuilder> {

  public PageService(DataRepository<PageEntity> repo,
      PagePredicateBuilder predicate,
      PageEmbeddingService embeddingService,
      PageMediaService mediaService) {
    super(repo, predicate);
    
    addService("embeddings", embeddingService);
    addService("uploads", mediaService);
  }
}
