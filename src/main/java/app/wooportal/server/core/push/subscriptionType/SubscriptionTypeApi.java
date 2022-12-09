package app.wooportal.server.core.push.subscriptionType;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SubscriptionTypeApi extends CrudApi<SubscriptionTypeEntity, SubscriptionTypeService> {
  
  public SubscriptionTypeApi(
      SubscriptionTypeService SubscriptionTypeService) {
    super(SubscriptionTypeService);
  }
  
  @Override
  @GraphQLQuery(name = "getSubscriptionTypes")
  @ApprovedAndVerifiedPermission
  public PageableList<SubscriptionTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getSubscriptionType")
  @ApprovedAndVerifiedPermission
  public Optional<SubscriptionTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SubscriptionTypeEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveSubscriptionTypes")
  @ApprovedAndVerifiedPermission
  public List<SubscriptionTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SubscriptionTypeEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveSubscriptionType")
  @ApprovedAndVerifiedPermission
  public SubscriptionTypeEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) SubscriptionTypeEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteSubscriptionTypes")
  @ApprovedAndVerifiedPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }
  
  @Override
  @GraphQLMutation(name = "deleteSubscriptionType")
  @ApprovedAndVerifiedPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  }


