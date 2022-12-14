package app.wooportal.server.features.deals.category;

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
public class DealCategoryApi extends CrudApi<DealCategoryEntity, DealCategoryService> {


  public DealCategoryApi(DealCategoryService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getDealCategories")
  @ApprovedAndVerifiedPermission
  public PageableList<DealCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getDealCategory")
  @ApprovedAndVerifiedPermission
  public Optional<DealCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) DealCategoryEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveDealCategories")
  @AdminPermission
  public List<DealCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<DealCategoryEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveDealCategory")
  public DealCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) DealCategoryEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteDealCategories")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteDealCategory")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
