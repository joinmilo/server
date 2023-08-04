package app.wooportal.server.base.thirdparty.socialMedia;

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
public class SocialMediaApi extends CrudApi<SocialMediaEntity, SocialMediaService> {

  public SocialMediaApi(SocialMediaService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getSocialMedias")
  public PageableList<SocialMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSocialMedia")
  public Optional<SocialMediaEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SocialMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSocialMedias")
  @AdminPermission
  public List<SocialMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SocialMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSocialMedia")
  public SocialMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) SocialMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSocialMedias")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSocialMedia")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
