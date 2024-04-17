package app.milo.server.base.cms.components.page.embeddingType;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class PageEmbeddingTypeService
    extends DataService<PageEmbeddingTypeEntity, PageEmbeddingTypePredicateBuilder> {

  public PageEmbeddingTypeService(
      DataRepository<PageEmbeddingTypeEntity> repo,
      PageEmbeddingTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
