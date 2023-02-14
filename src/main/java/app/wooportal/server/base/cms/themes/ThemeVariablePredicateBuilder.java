package app.wooportal.server.base.cms.themes;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.features.contests.contestTypes.QContestTypeEntity;

@Service
public class ThemeVariablePredicateBuilder
    extends PredicateBuilder<QContestTypeEntity, ThemeVariableEntity> {

  public ThemeVariablePredicateBuilder() {
    super(QContestTypeEntity.contestTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term)
        .or(query.translatable.any().name.likeIgnoreCase(term));
  }
}
