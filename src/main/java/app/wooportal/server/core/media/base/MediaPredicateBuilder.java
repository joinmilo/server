package app.wooportal.server.core.media.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class MediaPredicateBuilder extends PredicateBuilder<QMediaEntity, MediaEntity> {

  public MediaPredicateBuilder() {
    super(QMediaEntity.mediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

}
