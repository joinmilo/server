package app.wooportal.server.features.organisation.base;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.features.calculateRating.RatingDto;
import app.wooportal.server.features.calculateRating.RatingService;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.rating.EventRatingEntity;
import app.wooportal.server.features.organisation.rating.OrganisationRatingEntity;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class OrganisationApi extends CrudApi<OrganisationEntity, OrganisationService> {

  private final RatingService ratingService;

  public OrganisationApi(OrganisationService userService,
      RatingService ratingService) {
    super(userService);

    this.ratingService = ratingService;
  }

  @Override
  @GraphQLQuery(name = "getOrganisations")
  public PageableList<OrganisationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganisation")
  public Optional<OrganisationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisations")
  @AdminPermission
  public List<OrganisationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganisationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisation")
  public OrganisationEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisations")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisation")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

  @GraphQLQuery(name = "calculatedRatings")
  public CompletableFuture<RatingDto> calculateAverageRating(
      @GraphQLContext OrganisationEntity organisation) {
    int[] scoresArray = organisation.getRatings().stream()
        .mapToInt(OrganisationRatingEntity::getScore).toArray();
    return ratingService.calculateRating(scoresArray);
  }
}
