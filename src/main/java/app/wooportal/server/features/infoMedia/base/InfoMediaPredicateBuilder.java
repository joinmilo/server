package app.wooportal.server.features.infoMedia.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class InfoMediaPredicateBuilder extends PredicateBuilder<QInfoMediaEntity, InfoMediaEntity> {

  public InfoMediaPredicateBuilder() {
    super(QInfoMediaEntity.infoMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term)
        .or(query.media.extension.likeIgnoreCase(term))
        .or(query.media.mimeType.likeIgnoreCase(term))
        .or(query.media.url.likeIgnoreCase(term))
        .or(query.category.translatables.any().name.likeIgnoreCase(term));
  }
}
