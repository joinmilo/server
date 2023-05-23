package app.wooportal.server.features.survey.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyMediaService
    extends DataService<SurveyMediaEntity, SurveyMediaPredicateBuilder> {

  public SurveyMediaService(DataRepository<SurveyMediaEntity> repo,
      SurveyMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
