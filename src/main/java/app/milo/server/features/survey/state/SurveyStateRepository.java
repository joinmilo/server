package app.milo.server.features.survey.state;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface SurveyStateRepository extends DataRepository<SurveyStateEntity> {

}
