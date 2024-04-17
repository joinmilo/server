package app.milo.server.core.media.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class MediaPredicateBuilder extends PredicateBuilder<QMediaEntity, MediaEntity> {

  public MediaPredicateBuilder() {
    super(QMediaEntity.mediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term)
        .or(query.extension.likeIgnoreCase(term))
        .or(query.mimeType.likeIgnoreCase(term));
  }

}
