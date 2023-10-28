package app.wooportal.server.features.organisation.base;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface OrganisationRepository extends DataRepository<OrganisationEntity> {

}
