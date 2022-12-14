package app.wooportal.server.base.userContexts.base;

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

    return null;
  }
}
