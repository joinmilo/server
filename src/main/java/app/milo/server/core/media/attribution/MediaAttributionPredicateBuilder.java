package app.milo.server.core.media.attribution;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class MediaAttributionPredicateBuilder extends PredicateBuilder<QMediaAttributionEntity, MediaAttributionEntity> {

  public MediaAttributionPredicateBuilder() {
    super(QMediaAttributionEntity.mediaAttributionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.title.likeIgnoreCase(term)
        .or(query.author.likeIgnoreCase(term))
        .or(query.source.likeIgnoreCase(term))
        .or(query.license.likeIgnoreCase(term));
  }

}
