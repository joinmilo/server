package app.wooportal.server.features.feedbacks.comment;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class CommentPredicateBuilder extends PredicateBuilder<QCommentEntity, CommentEntity> {

  public CommentPredicateBuilder() {
    super(QCommentEntity.commentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.user.id.likeIgnoreCase(term);
  }
}
