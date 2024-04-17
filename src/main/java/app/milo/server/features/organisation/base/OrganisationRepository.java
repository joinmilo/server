package app.milo.server.features.organisation.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface OrganisationRepository extends DataRepository<OrganisationEntity> {

}
