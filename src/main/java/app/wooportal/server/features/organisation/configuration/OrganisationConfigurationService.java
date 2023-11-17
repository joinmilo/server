package app.wooportal.server.features.organisation.configuration;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationConfigurationService extends DataService<OrganisationConfigurationEntity, OrganisationConfigurationBuilder> {

  public OrganisationConfigurationService(DataRepository<OrganisationConfigurationEntity> repo, OrganisationConfigurationBuilder predicate) {
    super(repo, predicate);
  }
}
