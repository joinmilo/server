package app.milo.server.features.survey.result;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface SurveyResultRepository extends DataRepository<SurveyResultEntity> {

}
