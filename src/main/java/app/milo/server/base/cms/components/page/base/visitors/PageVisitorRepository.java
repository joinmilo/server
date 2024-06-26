package app.milo.server.base.cms.components.page.base.visitors;

import org.springframework.stereotype.Repository;
import app.milo.server.core.visit.visitable.VisitableRepository;

@Repository
public interface PageVisitorRepository extends VisitableRepository<PageVisitorEntity> {

}
