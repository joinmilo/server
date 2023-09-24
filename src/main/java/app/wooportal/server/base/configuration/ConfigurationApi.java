package app.wooportal.server.base.configuration;

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
public class ConfigurationApi extends CrudApi<ConfigurationEntity, ConfigurationService> {

  public ConfigurationApi(ConfigurationService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getConfigurations")
  public PageableList<ConfigurationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getConfiguration")
  public Optional<ConfigurationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ConfigurationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveConfigurations")
  public List<ConfigurationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ConfigurationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveConfiguration")
  public ConfigurationEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ConfigurationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteConfigurations")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteConfiguration")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
