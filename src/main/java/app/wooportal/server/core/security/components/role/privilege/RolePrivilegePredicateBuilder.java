package app.wooportal.server.core.security.components.role.privilege;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;


@Service
public class RolePrivilegePredicateBuilder
    extends PredicateBuilder<QRolePrivilegeEntity, RolePrivilegeEntity> {

  public RolePrivilegePredicateBuilder() {
    super(QRolePrivilegeEntity.rolePrivilegeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.description.likeIgnoreCase(term).or(query.name.likeIgnoreCase(term));
  }

  public BooleanExpression withCode(String code) {
    return query.code.eq(code);
  }

  public BooleanExpression withUserAndCode(String id, String code) {
    return id != null && code != null
        ? query.roles.any().users.any().id.eq(id).and(query.code.eq(code))
        : null;
  }
}

