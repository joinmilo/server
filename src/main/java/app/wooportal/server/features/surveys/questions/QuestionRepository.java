package app.wooportal.server.features.surveys.questions;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends DataRepository<QuestionEntity> {

}
