package app.milo.server.core.security.components.role.privilege;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class RolePrivilegeService
    extends DataService<RolePrivilegeEntity, RolePrivilegePredicateBuilder> {

  public RolePrivilegeService(DataRepository<RolePrivilegeEntity> repo,
      RolePrivilegePredicateBuilder predicate) {
    super(repo, predicate);

  }

  @Override
  public Optional<RolePrivilegeEntity> getExisting(RolePrivilegeEntity entity) {
    return entity != null && entity.getCode() != null && !entity.getCode().isBlank()
        ? repo.findOne(singleQuery(predicate.withCode(entity.getCode())))
        : Optional.empty();
  }
}
