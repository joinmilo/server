package app.milo.server.features.survey.questionType;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyQuestionTypeService extends DataService<SurveyQuestionTypeEntity, SurveyQuestionTypePredicateBuilder> {

  public SurveyQuestionTypeService(DataRepository<SurveyQuestionTypeEntity> repo, SurveyQuestionTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
