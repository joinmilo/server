package app.milo.server.features.survey.question;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyQuestionService extends DataService<SurveyQuestionEntity, SurveyQuestionPredicateBuilder> {

  public SurveyQuestionService(DataRepository<SurveyQuestionEntity> repo, SurveyQuestionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
