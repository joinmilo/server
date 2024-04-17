package app.milo.server.base.userContext.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class UserContextPredicateBuilder
    extends PredicateBuilder<QUserContextEntity, UserContextEntity> {

  public UserContextPredicateBuilder() {
    super(QUserContextEntity.userContextEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.user.email.likeIgnoreCase(term)
        .or(query.user.lastName.likeIgnoreCase(term))
        .or(query.translatables.any().description.likeIgnoreCase(term))
        .or(query.user.firstName.likeIgnoreCase(term))
        .or(query.user.id.likeIgnoreCase(term))
        .or(query.uploads.any().media.id.likeIgnoreCase(term));
  }
  
  public BooleanExpression withUser(String userId) {
    return query.user.id.eq(userId);
  }
  
  public BooleanExpression withEmail(String email) {
    return email != null && !email.isBlank()
        ? query.user.email.equalsIgnoreCase(email)
        : null;
  }
  
}
