package app.wooportal.server.core.security.components.role.application;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class RoleApplicationPredicateBuilder extends PredicateBuilder<QRoleApplicationEntity, RoleApplicationEntity> {

  public RoleApplicationPredicateBuilder() {
    super(QRoleApplicationEntity.roleApplicationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.user.id.likeIgnoreCase(term);
  }
}

