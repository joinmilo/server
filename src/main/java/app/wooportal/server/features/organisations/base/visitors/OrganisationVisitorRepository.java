package app.wooportal.server.features.organisations.base.visitors;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationVisitorRepository extends DataRepository<OrganisationVisitorEntity> {

}
