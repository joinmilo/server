package app.wooportal.server.base.cms.page.widget;

import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.page.widgetAttribute.PageWidgetAttributeService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageWidgetService
    extends DataService<PageWidgetEntity, PageWidgetPredicateBuilder> {

  public PageWidgetService(
      DataRepository<PageWidgetEntity> repo,
      PageWidgetPredicateBuilder predicate,
      PageWidgetAttributeService pageWidgetAttributeService) {
    super(repo, predicate);
    
    addService("attributes", pageWidgetAttributeService);
  }
}
