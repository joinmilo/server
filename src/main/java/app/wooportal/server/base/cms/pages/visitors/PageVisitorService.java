package app.wooportal.server.base.cms.pages.visitors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageVisitorService extends DataService<PageVisitorEntity, PageVisitorPredicateBuilder> {

  public PageVisitorService(DataRepository<PageVisitorEntity> repo, PageVisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
