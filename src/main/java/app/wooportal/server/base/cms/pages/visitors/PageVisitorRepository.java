package app.wooportal.server.base.cms.pages.visitors;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface PageVisitorRepository extends VisitableRepository<PageVisitorEntity> {

}
