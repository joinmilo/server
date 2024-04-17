package app.milo.server.core.security.components.userDeletion.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class UserDeletionPredicateBuilder extends PredicateBuilder<QUserDeletionEntity, UserDeletionEntity> {

  public UserDeletionPredicateBuilder() {
    super(QUserDeletionEntity.userDeletionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().content.likeIgnoreCase(term);
  }
}
