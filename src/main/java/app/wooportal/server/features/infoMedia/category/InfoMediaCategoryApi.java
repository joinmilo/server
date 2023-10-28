package app.wooportal.server.features.infoMedia.category;

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
public class InfoMediaCategoryApi extends CrudApi<InfoMediaCategoryEntity, InfoMediaCategoryService> {

  public InfoMediaCategoryApi(InfoMediaCategoryService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getInfoMediaCategories")
  public PageableList<InfoMediaCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getInfoMediaCategory")
  public Optional<InfoMediaCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) InfoMediaCategoryEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveInfoMediaCategories")
  public List<InfoMediaCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<InfoMediaCategoryEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveInfoMediaCategory")
  public InfoMediaCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) InfoMediaCategoryEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteInfoMediaCategories")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteInfoMediaCategory")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
