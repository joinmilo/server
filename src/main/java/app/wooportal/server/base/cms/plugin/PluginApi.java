package app.wooportal.server.base.cms.plugin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.cms.authorization.permissions.CmsAdminPermission;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class PluginApi extends CrudApi<PluginEntity, PluginService> {

  public PluginApi(PluginService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getPlugins")
  public PageableList<PluginEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPlugin")
  public Optional<PluginEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PluginEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePlugins")
  @CmsAdminPermission
  public List<PluginEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PluginEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePlugin")
  @CmsAdminPermission
  public PluginEntity saveOne(@GraphQLArgument(name = CrudApi.entity) PluginEntity entity) {
    return super.saveOne(entity);
  }
  
  @GraphQLMutation(name = "changePluginActivation")
  @CmsAdminPermission
  public Boolean changePluginActivation(String pluginId, Boolean active) {
    return service.changeActivation(pluginId, active);
  }
}
