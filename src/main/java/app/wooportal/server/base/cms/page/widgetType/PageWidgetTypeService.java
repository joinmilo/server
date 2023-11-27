package app.wooportal.server.base.cms.page.widgetType;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageWidgetTypeService
    extends DataService<PageWidgetTypeEntity, PageWidgetTypePredicateBuilder> {

  public PageWidgetTypeService(
      DataRepository<PageWidgetTypeEntity> repo,
      PageWidgetTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
