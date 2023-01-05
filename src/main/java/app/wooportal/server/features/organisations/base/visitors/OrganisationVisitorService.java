package app.wooportal.server.features.organisations.base.visitors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationVisitorService extends DataService<OrganisationVisitorEntity, OrganisationVisitorPredicateBuilder> {

  public OrganisationVisitorService(DataRepository<OrganisationVisitorEntity> repo, OrganisationVisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
