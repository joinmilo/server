package app.wooportal.server.features.surveys.base.visitors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyVisitorService extends DataService<SurveyVisitorEntity, SurveyVisitorPredicateBuilder> {

  public SurveyVisitorService(DataRepository<SurveyVisitorEntity> repo, SurveyVisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
