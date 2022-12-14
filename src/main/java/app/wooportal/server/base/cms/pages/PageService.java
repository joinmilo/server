package app.wooportal.server.base.cms.pages;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageService
    extends DataService<PageEntity, PagePredicateBuilder> {

  public PageService(DataRepository<PageEntity> repo,
      PagePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
