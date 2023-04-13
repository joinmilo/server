package app.wooportal.server.core.security.components.user.passwordReset;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PasswordResetBuilder extends PredicateBuilder<QPasswordResetEntity, PasswordResetEntity> {

  public PasswordResetBuilder() {
    super(QPasswordResetEntity.passwordResetEntity);
  }
  
  @Override
  public BooleanExpression freeSearch(String term) {
    return query.token.likeIgnoreCase(term);
  }
  
  public BooleanExpression withToken(String token) {
    return token != null && !token.isBlank()
        ? query.token.eq(token)
        : null;
  }

  public BooleanExpression createdBefore(OffsetDateTime date) {
    return date != null
        ? query.created.before(date)
        : null;
  }

  public BooleanExpression withUser(String userId) {
    return userId != null
        ? query.token.eq(userId)
        : null;
  }

}
