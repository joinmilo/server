package app.wooportal.server.core.i18n.components.label;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class LabelPredicateBuilder extends PredicateBuilder<QLabelEntity, LabelEntity> {

  public LabelPredicateBuilder() {
    super(QLabelEntity.labelEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.tagId.likeIgnoreCase(term)
        .or(query.translatables.any().content.likeIgnoreCase(term));
  }

  public BooleanExpression withTagId(String tagId) {
    return query.tagId.eq(tagId);
  }
}
