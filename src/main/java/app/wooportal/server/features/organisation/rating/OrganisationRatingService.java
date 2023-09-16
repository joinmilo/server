package app.wooportal.server.features.organisation.rating;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.organisation.base.OrganisationEntity;

@Service
public class OrganisationRatingService extends DataService<OrganisationRatingEntity, OrganisationRatingPredicateBuilder> {

  public OrganisationRatingService(DataRepository<OrganisationRatingEntity> repo, OrganisationRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }

  public List<OrganisationRatingEntity> getAllBetween(
      OrganisationEntity parent,
      OffsetDateTime startDate,
      OffsetDateTime endDate) {
    return parent != null
        ? repo.findAll(collectionQuery(predicate.withParentId(parent.getId()))
            .and(predicate.modifiedBetween(startDate, endDate))
          ).getList()
        : List.of();
  }
}
