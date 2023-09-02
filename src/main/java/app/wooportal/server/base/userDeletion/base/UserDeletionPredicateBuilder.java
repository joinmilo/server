package app.wooportal.server.base.userDeletion.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

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
