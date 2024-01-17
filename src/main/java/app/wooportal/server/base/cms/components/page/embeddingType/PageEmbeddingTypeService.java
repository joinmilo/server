package app.wooportal.server.base.cms.components.page.embeddingType;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageEmbeddingTypeService
    extends DataService<PageEmbeddingTypeEntity, PageEmbeddingTypePredicateBuilder> {

  public PageEmbeddingTypeService(
      DataRepository<PageEmbeddingTypeEntity> repo,
      PageEmbeddingTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
