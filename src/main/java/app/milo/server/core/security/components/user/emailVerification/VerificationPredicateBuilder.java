package app.milo.server.core.security.components.user.emailVerification;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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

  public BooleanExpression withToken(String token) {
    return token != null
        ? query.token.eq(token)
        : null;
  }

  public BooleanExpression withUser(String userId) {
    return userId != null
        ? query.user.id.eq(userId)
        : null;
  }

}
