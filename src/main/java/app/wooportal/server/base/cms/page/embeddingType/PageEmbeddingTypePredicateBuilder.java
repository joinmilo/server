package app.wooportal.server.base.cms.page.embeddingType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageEmbeddingTypePredicateBuilder
    extends PredicateBuilder<QPageEmbeddingTypeEntity, PageEmbeddingTypeEntity> {

  public PageEmbeddingTypePredicateBuilder() {
    super(QPageEmbeddingTypeEntity.pageEmbeddingTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }
}
