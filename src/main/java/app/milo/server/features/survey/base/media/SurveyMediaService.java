package app.milo.server.features.survey.base.media;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyMediaService
    extends DataService<SurveyMediaEntity, SurveyMediaPredicateBuilder> {

  public SurveyMediaService(DataRepository<SurveyMediaEntity> repo,
      SurveyMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
