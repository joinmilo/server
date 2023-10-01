package app.wooportal.server.base.cms.theme;

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
public class ThemeApi extends CrudApi<ThemeEntity, ThemeService> {

  public ThemeApi(ThemeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getThemes")
  public PageableList<ThemeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getTheme")
  public Optional<ThemeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ThemeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveThemes")
  public List<ThemeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ThemeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveTheme")
  public ThemeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ThemeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteThemes")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteTheme")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
