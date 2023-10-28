package app.wooportal.server.core.security.components.role.application;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;


@Service
public class PrivilegeApplicationPredicateBuilder extends PredicateBuilder<QPrivilegeApplicationEntity, PrivilegeApplicationEntity> {

  public PrivilegeApplicationPredicateBuilder() {
    super(QPrivilegeApplicationEntity.privilegeApplicationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.user.id.likeIgnoreCase(term);
  }
}

