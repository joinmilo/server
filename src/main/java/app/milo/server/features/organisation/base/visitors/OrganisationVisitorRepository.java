package app.milo.server.features.organisation.base.visitors;

import org.springframework.stereotype.Repository;
import app.milo.server.core.visit.visitable.VisitableRepository;

@Repository
public interface OrganisationVisitorRepository extends VisitableRepository<OrganisationVisitorEntity> {

}
