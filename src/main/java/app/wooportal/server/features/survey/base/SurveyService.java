package app.wooportal.server.features.survey.base;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyService extends DataService<SurveyEntity, SurveyPredicateBuilder> {

  public SurveyService(DataRepository<SurveyEntity> repo, SurveyPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  public Boolean sponsorSurvey(String surveyId) {
    var survey = getById(surveyId);
    
    if (survey.isPresent()) {
      survey.get().setSponsored(true);
      repo.save(survey.get());
      
      unsponsorOthers(surveyId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOthers(String surveyId) {
    var others = readAll(collectionQuery(predicate.withoutId(surveyId)));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(survey -> {
        survey.setSponsored(false);
        repo.save(survey);
      });
    }
  }
}
