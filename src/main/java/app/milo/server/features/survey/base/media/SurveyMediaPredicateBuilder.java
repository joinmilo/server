package app.milo.server.features.survey.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SurveyMediaPredicateBuilder
    extends PredicateBuilder<QSurveyMediaEntity, SurveyMediaEntity> {

  public SurveyMediaPredicateBuilder() {
    super(QSurveyMediaEntity.surveyMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
