package app.wooportal.server.core.security.components.role.rolePrivilege;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;


@Service
public class RolePrivilegePredicateBuilder extends PredicateBuilder<QRolePrivilegeEntity, RolePrivilegeEntity> {

  public RolePrivilegePredicateBuilder() {
    super(QRolePrivilegeEntity.rolePrivilegeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.description.likeIgnoreCase(term)
        .or(query.name.likeIgnoreCase(term));
  }
}

