package app.milo.server.base.userContext.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class UserContextMediaPredicateBuilder
    extends PredicateBuilder<QUserContextMediaEntity, UserContextMediaEntity> {

  public UserContextMediaPredicateBuilder() {
    super(QUserContextMediaEntity.userContextMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
  
  
  public BooleanExpression withUserContext(String userContextId) {
    return query.userContext.id.eq(userContextId);
    
  }

  public BooleanExpression withMedia(String mediaId) {
    return mediaId != null
        ? query.media.id.eq(mediaId)
        : null;
  }
}
