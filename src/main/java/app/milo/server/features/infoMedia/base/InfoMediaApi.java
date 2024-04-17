package app.milo.server.features.infoMedia.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.features.infoMedia.authorization.permissions.MediaAdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class InfoMediaApi extends CrudApi<InfoMediaEntity, InfoMediaService> {

  public InfoMediaApi(
      InfoMediaService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getInfoMedia")
  public PageableList<InfoMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getInfoMedium")
  public Optional<InfoMediaEntity> readOne(@GraphQLArgument(name = CrudApi.entity) InfoMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveInfoMedia")
  @MediaAdminPermission
  public List<InfoMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<InfoMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveInfoMedium")
  @MediaAdminPermission
  public InfoMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) InfoMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteInfoMedia")
  @MediaAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteInfoMedium")
  @MediaAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}
