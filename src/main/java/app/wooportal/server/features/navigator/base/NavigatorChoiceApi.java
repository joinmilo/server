package app.wooportal.server.features.navigator.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.comment.EventCommentEntity;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class NavigatorChoiceApi extends CrudApi<NavigatorChoiceEntity, NavigatorChoiceService> {

  public NavigatorChoiceApi(NavigatorChoiceService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getNavigatorNodes")
  public PageableList<NavigatorChoiceEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getNavigatorNode")
  public Optional<NavigatorChoiceEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) NavigatorChoiceEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveNavigatorNodes")
  @AdminPermission
  public List<NavigatorChoiceEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<NavigatorChoiceEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveNavigatorNode")
  @AdminPermission
  public NavigatorChoiceEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) NavigatorChoiceEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteNavigatorNodes")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteNavigatorNode")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
//  
//  @GraphQLQuery(name = "possibleChildNodes")
//  public List<NavigatorChoiceEntity> possibleChildNodes(
//      @GraphQLContext NavigatorChoiceEntity node) {
//    return service.getPossibleNodes(node);
//  }
}
