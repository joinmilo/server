package app.milo.server.features.deal.components.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.features.deal.authorization.permissions.DealAdminPermission;
import app.milo.server.features.deal.authorization.permissions.DealManagePermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class DealApi extends CrudApi<DealEntity, DealService> {

  public DealApi(DealService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getDeals")
  public PageableList<DealEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getDeal")
  public Optional<DealEntity> readOne(@GraphQLArgument(name = CrudApi.entity) DealEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveDeals")
  @DealManagePermission
  public List<DealEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<DealEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveDeal")
  @DealManagePermission
  public DealEntity saveOne(@GraphQLArgument(name = CrudApi.entity) DealEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteDeals")
  @DealManagePermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteDeal")
  @DealManagePermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "sponsorDeal")
  @DealAdminPermission
  public Boolean sponsorDeal(String dealId) {
    return service.sponsor(dealId);
  }
}
