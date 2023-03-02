package app.wooportal.server.features.organisation.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationService extends DataService<OrganisationEntity, OrganisationPredicateBuilder> {

  public OrganisationService(DataRepository<OrganisationEntity> repo, OrganisationPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
