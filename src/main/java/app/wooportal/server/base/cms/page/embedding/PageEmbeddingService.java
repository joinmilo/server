package app.wooportal.server.base.cms.page.embedding;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageEmbeddingService
    extends DataService<PageEmbeddingEntity, PageEmbeddingPredicateBuilder> {

  public PageEmbeddingService(DataRepository<PageEmbeddingEntity> repo,
      PageEmbeddingPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
