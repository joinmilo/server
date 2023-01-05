package app.wooportal.server.features.surveys.base.visitors;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyVisitorRepository extends DataRepository<SurveyVisitorEntity> {

}
