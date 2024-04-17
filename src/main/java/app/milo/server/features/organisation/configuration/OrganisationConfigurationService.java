package app.milo.server.features.organisation.configuration;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class OrganisationConfigurationService extends DataService<OrganisationConfigurationEntity, OrganisationConfigurationBuilder> {

  public OrganisationConfigurationService(DataRepository<OrganisationConfigurationEntity> repo, OrganisationConfigurationBuilder predicate) {
    super(repo, predicate);
  }
}
