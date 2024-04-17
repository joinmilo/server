package app.milo.server.features.survey.question;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface SurveyQuestionRepository extends DataRepository<SurveyQuestionEntity> {

}
