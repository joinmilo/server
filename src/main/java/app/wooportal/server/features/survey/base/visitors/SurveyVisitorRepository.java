package app.wooportal.server.features.survey.base.visitors;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface SurveyVisitorRepository extends VisitableRepository<SurveyVisitorEntity> {

}
