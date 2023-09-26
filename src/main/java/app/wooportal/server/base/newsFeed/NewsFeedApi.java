package app.wooportal.server.base.newsFeed;

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
public class NewsFeedApi extends CrudApi<NewsFeedEntity, NewsFeedService> {


  public NewsFeedApi(NewsFeedService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getNewsFeeds")
  public PageableList<NewsFeedEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getNewsFeed")
  public Optional<NewsFeedEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) NewsFeedEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveNewsFeeds")
  @AdminPermission
  public List<NewsFeedEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<NewsFeedEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveNewsFeed")
  public NewsFeedEntity saveOne(@GraphQLArgument(name = CrudApi.entity) NewsFeedEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteNewsFeeds")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteNewsFeed")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
