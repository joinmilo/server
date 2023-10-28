package app.wooportal.server.base.adminFooter.parent;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AdminFooterParentService
    extends DataService<AdminFooterParentEntity, AdminFooterParentBuilder> {

  public AdminFooterParentService(
      DataRepository<AdminFooterParentEntity> repo,
      AdminFooterParentBuilder predicate) {
    super(repo, predicate);
  }
}
