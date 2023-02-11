package app.wooportal.server.base.cms.pageFeatures;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageFeatureService
    extends DataService<PageFeatureEntity, PageFeaturePredicateBuilder> {

  public PageFeatureService(DataRepository<PageFeatureEntity> repo,
      PageFeaturePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
