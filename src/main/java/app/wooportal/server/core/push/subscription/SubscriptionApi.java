package app.wooportal.server.core.push.subscription;

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
public class SubscriptionApi extends CrudApi<SubscriptionEntity, SubscriptionService> {

  public SubscriptionApi(SubscriptionService subscriptionService) {
    super(subscriptionService);
  }

  @Override
  @GraphQLQuery(name = "getSubscriptions")
  public PageableList<SubscriptionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSubscription")
  public Optional<SubscriptionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SubscriptionEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSubscriptions")
  public List<SubscriptionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SubscriptionEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSubscription")
  public SubscriptionEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) SubscriptionEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSubscriptions")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSubscription")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


