package app.wooportal.server.features.deals.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class DealApi extends CrudApi<DealEntity, DealService> {


  public DealApi(DealService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getDeals")
  @ApprovedAndVerifiedPermission
  public PageableList<DealEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getDeal")
  @ApprovedAndVerifiedPermission
  public Optional<DealEntity> readOne(@GraphQLArgument(name = CrudApi.entity) DealEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveDeals")
  @AdminPermission
  public List<DealEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<DealEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveDeal")
  public DealEntity saveOne(@GraphQLArgument(name = CrudApi.entity) DealEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteDeals")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteDeal")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
