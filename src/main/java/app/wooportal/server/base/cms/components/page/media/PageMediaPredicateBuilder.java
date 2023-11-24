package app.wooportal.server.base.cms.components.page.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageMediaPredicateBuilder
    extends PredicateBuilder<QPageMediaEntity, PageMediaEntity> {

  public PageMediaPredicateBuilder() {
    super(QPageMediaEntity.pageMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
  
  public BooleanExpression withPage(String articleId) {
    return articleId != null
        ? query.page.id.eq(articleId)
        : null;
  }

  public BooleanExpression withMedia(String mediaId) {
    return mediaId != null
        ? query.media.id.eq(mediaId)
        : null;
  }
}
