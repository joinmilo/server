package app.wooportal.server.base.cms.feature;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.cms.authorization.permissions.CmsAdminPermission;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class FeatureApi extends CrudApi<FeatureEntity, FeatureService> {

  public FeatureApi(FeatureService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getFeatures")
  public PageableList<FeatureEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getFeature")
  public Optional<FeatureEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) FeatureEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveFeatures")
  @CmsAdminPermission
  public List<FeatureEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<FeatureEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveFeature")
  @CmsAdminPermission
  public FeatureEntity saveOne(@GraphQLArgument(name = CrudApi.entity) FeatureEntity entity) {
    return super.saveOne(entity);
  }
  
  @GraphQLMutation(name = "changeActivation")
  @CmsAdminPermission
  public Boolean changeActivation(String featureId, Boolean active) {
    return service.changeActivation(featureId, active);
  }
}
