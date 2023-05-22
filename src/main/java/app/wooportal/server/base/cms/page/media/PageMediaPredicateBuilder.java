package app.wooportal.server.base.cms.page.media;

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
}
