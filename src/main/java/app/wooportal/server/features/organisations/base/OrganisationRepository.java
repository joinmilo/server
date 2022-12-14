package app.wooportal.server.features.organisations.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends DataRepository<OrganisationEntity> {

}
