package app.wooportal.server.base.cms.components.page.attributeType;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageAttributeTypeService
    extends DataService<PageAttributeTypeEntity, PageAttributeTypePredicateBuilder> {

  public PageAttributeTypeService(
      DataRepository<PageAttributeTypeEntity> repo,
      PageAttributeTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
