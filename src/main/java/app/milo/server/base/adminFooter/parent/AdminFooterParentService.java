package app.milo.server.base.adminFooter.parent;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class AdminFooterParentService
    extends DataService<AdminFooterParentEntity, AdminFooterParentBuilder> {

  public AdminFooterParentService(
      DataRepository<AdminFooterParentEntity> repo,
      AdminFooterParentBuilder predicate) {
    super(repo, predicate);
  }
}
