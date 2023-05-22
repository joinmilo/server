package app.wooportal.server.base.cms.page.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageMediaService
    extends DataService<PageMediaEntity, PageMediaPredicateBuilder> {

  public PageMediaService(DataRepository<PageMediaEntity> repo,
      PageMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
