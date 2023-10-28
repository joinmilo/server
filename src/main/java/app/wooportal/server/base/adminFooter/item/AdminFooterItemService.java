package app.wooportal.server.base.adminFooter.item;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AdminFooterItemService
    extends DataService<AdminFooterItemEntity, AdminFooterItemBuilder> {

  public AdminFooterItemService(
      DataRepository<AdminFooterItemEntity> repo,
      AdminFooterItemBuilder predicate) {
    super(repo, predicate);
  }
}
