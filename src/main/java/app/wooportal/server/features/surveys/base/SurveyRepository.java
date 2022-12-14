package app.wooportal.server.features.surveys.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends DataRepository<SurveyEntity> {

}
