package app.wooportal.server.features.organisation.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationMediaService
    extends DataService<OrganisationMediaEntity, OrganisationMediaPredicateBuilder> {

  public OrganisationMediaService(DataRepository<OrganisationMediaEntity> repo,
      OrganisationMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
