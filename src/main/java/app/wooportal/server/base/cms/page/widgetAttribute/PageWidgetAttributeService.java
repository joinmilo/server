package app.wooportal.server.base.cms.page.widgetAttribute;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageWidgetAttributeService
    extends DataService<PageWidgetAttributeEntity, PageWidgetAttributePredicateBuilder> {

  public PageWidgetAttributeService(
      DataRepository<PageWidgetAttributeEntity> repo,
      PageWidgetAttributePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
