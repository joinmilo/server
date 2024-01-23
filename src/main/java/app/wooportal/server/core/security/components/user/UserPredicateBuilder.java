package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class UserPredicateBuilder extends PredicateBuilder<QUserEntity, UserEntity> {

  public UserPredicateBuilder() {
    super(QUserEntity.userEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.email.likeIgnoreCase(term).or(query.roles.any().translatables.any().name.likeIgnoreCase(term));
  }

  public BooleanExpression withEmail(String loginName) {
    return loginName != null && !loginName.isBlank()
        ? query.email.equalsIgnoreCase(loginName)
        : null;
  }

  public BooleanExpression createdBefore(OffsetDateTime date) {
    return date != null ? query.created.before(date) : null;
  }

  public BooleanExpression notVerified() {
    return query.verified.isFalse();
  }

  public BooleanExpression withUserAndPrivilege(String userId, String code) {
    return query.id.eq(userId).and(query.roles.any().privileges.any().code.eq(code));
  }
  
  public BooleanExpression withPrivilege(String... code) {
    return query.roles.any().privileges.any().code.in(code);
  }
  
  public BooleanExpression hasAnyAdminPrivilege(String userId) {
    return query.id.eq(userId).and(query.roles.any().privileges.any().code.contains("admin"));
  }
  
}
