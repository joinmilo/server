package app.milo.server.features.survey.base.visitors;

import org.springframework.stereotype.Repository;
import app.milo.server.core.visit.visitable.VisitableRepository;

@Repository
public interface SurveyVisitorRepository extends VisitableRepository<SurveyVisitorEntity> {

}
