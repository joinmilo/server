package app.wooportal.server.features.organisations.base.visitors;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface OrganisationVisitorRepository extends VisitableRepository<OrganisationVisitorEntity> {

}
