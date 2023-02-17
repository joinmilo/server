package app.wooportal.server.base.cms.pages;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.features.contests.types.QContestTypeEntity;

@Service
public class PagePredicateBuilder
    extends PredicateBuilder<QContestTypeEntity, PageEntity> {

  public PagePredicateBuilder() {
    super(QContestTypeEntity.contestTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term).or(query.translatables.any().name.likeIgnoreCase(term));
  }
}
