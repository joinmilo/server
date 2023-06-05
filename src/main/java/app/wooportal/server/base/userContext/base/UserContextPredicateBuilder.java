package app.wooportal.server.base.userContext.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

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
        .or(query.avatar.id.likeIgnoreCase(term));
  }
  
  public BooleanExpression withUser(String userId) {
    return query.user.id.eq(userId);
  }
}
