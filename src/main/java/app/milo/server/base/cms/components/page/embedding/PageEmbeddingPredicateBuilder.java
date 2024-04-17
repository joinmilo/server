package app.milo.server.base.cms.components.page.embedding;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class PageEmbeddingPredicateBuilder
    extends PredicateBuilder<QPageEmbeddingEntity, PageEmbeddingEntity> {

  public PageEmbeddingPredicateBuilder() {
    super(QPageEmbeddingEntity.pageEmbeddingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.page.label.likeIgnoreCase(term);
  }
}
