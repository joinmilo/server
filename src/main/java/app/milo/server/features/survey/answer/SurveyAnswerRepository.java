package app.milo.server.features.survey.answer;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface SurveyAnswerRepository extends DataRepository<SurveyAnswerEntity> {

}
