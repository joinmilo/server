package app.wooportal.server.base.cms.page.embedding;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageEmbeddingPredicateBuilder
    extends PredicateBuilder<QPageEmbeddingEntity, PageEmbeddingEntity> {

  public PageEmbeddingPredicateBuilder() {
    super(QPageEmbeddingEntity.pageEmbeddingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.page.translatables.any().name.likeIgnoreCase(term);
  }
}
