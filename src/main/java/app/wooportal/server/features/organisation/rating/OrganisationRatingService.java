package app.wooportal.server.features.organisation.rating;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationRatingService extends DataService<OrganisationRatingEntity, OrganisationRatingPredicateBuilder> {

  public OrganisationRatingService(DataRepository<OrganisationRatingEntity> repo, OrganisationRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
