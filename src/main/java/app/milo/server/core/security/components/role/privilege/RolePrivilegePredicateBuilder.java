package app.milo.server.core.security.components.role.privilege;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;


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
  
}

