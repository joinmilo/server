package app.wooportal.server.features.organisation.rating;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.wooportal.server.base.userContext.base.UserContextService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.organisation.base.OrganisationEntity;

@Service
public class OrganisationRatingService extends DataService<OrganisationRatingEntity, OrganisationRatingPredicateBuilder> {

  public OrganisationRatingService(DataRepository<OrganisationRatingEntity>
  repo, OrganisationRatingPredicateBuilder predicate,
  UserContextService userContextService) {
    super(repo, predicate);
    
    addService("userContext", userContextService);
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
  
  @Override
  public Optional<OrganisationRatingEntity> getExisting(OrganisationRatingEntity entity) {
    return entity != null
        && entity.getUserContext() != null
        && entity.getUserContext().getId() != null
        && entity.getParent() != null
        && entity.getParent().getId() != null
       ? repo.findOne(singleQuery(predicate.withUserContext(entity.getUserContext().getId()))
           .and(predicate.withParentId(entity.getParent().getId()))
         )
       : Optional.empty();
  }
}
