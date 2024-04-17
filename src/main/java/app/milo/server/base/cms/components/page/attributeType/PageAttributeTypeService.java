package app.milo.server.base.cms.components.page.attributeType;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class PageAttributeTypeService
    extends DataService<PageAttributeTypeEntity, PageAttributeTypePredicateBuilder> {

  public PageAttributeTypeService(
      DataRepository<PageAttributeTypeEntity> repo,
      PageAttributeTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
