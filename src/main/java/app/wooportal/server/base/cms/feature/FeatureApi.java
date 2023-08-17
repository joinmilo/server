package app.wooportal.server.base.cms.feature;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
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
  @AdminPermission
  public List<FeatureEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<FeatureEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveFeature")
  @AdminPermission
  public FeatureEntity saveOne(@GraphQLArgument(name = CrudApi.entity) FeatureEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteFeatures")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteFeature")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
