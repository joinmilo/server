package app.milo.server.features.survey.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface SurveyRepository extends DataRepository<SurveyEntity> {

}
