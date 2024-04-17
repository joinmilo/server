package app.milo.server.base.adminFooter.item;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class AdminFooterItemService
    extends DataService<AdminFooterItemEntity, AdminFooterItemBuilder> {

  public AdminFooterItemService(
      DataRepository<AdminFooterItemEntity> repo,
      AdminFooterItemBuilder predicate) {
    super(repo, predicate);
  }
}
