package app.wooportal.server.core.security.components.role;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class RolePredicateBuilder extends PredicateBuilder<QRoleEntity, RoleEntity> {

  public RolePredicateBuilder() {
    super(QRoleEntity.roleEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }

  public BooleanExpression withUserId(String userId) {
    return query.users.any().id.eq(userId);
  }

  public BooleanExpression withKey(String name) {
    return query.key.eq(name);
  }
}

