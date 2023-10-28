package app.wooportal.server.base.adminFooter.parent;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class AdminFooterParentApi extends CrudApi<AdminFooterParentEntity, AdminFooterParentService> {

  public AdminFooterParentApi(AdminFooterParentService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getAdminFooterParents")
  public PageableList<AdminFooterParentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAdminFooterParent")
  public Optional<AdminFooterParentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AdminFooterParentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAdminFooterParents")
  public List<AdminFooterParentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AdminFooterParentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAdminFooterParent")
  public AdminFooterParentEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AdminFooterParentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAdminFooterParents")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAdminFooterParent")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
