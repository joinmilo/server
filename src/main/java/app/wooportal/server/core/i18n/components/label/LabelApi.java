package app.wooportal.server.core.i18n.components.label;

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
public class LabelApi extends CrudApi<LabelEntity, LabelService> {

  public LabelApi(LabelService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getLabels")
  public PageableList<LabelEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getLabel")
  public Optional<LabelEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) LabelEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveLabels")
  public List<LabelEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<LabelEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveLabel")
  public LabelEntity saveOne(@GraphQLArgument(name = CrudApi.entity) LabelEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteLabels")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteLabel")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
