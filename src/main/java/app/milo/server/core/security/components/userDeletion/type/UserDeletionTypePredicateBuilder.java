package app.milo.server.core.security.components.userDeletion.type;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class UserDeletionTypePredicateBuilder extends PredicateBuilder<QUserDeletionTypeEntity, UserDeletionTypeEntity> {

  public UserDeletionTypePredicateBuilder() {
    super(QUserDeletionTypeEntity.userDeletionTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }
}
