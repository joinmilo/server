package app.wooportal.server.core.security.components.user.emailVerification;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class VerificationPredicateBuilder
    extends PredicateBuilder<QVerificationEntity, VerificationEntity> {

  public VerificationPredicateBuilder() {
    super(QVerificationEntity.verificationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.token.likeIgnoreCase(term);
  }

  public BooleanExpression withKey(String key) {
    return key != null ? query.token.eq(key) : null;
  }

  public BooleanExpression withUser(String userId) {
    return userId != null ? query.token.eq(userId) : null;
  }

}
