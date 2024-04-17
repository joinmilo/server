package app.milo.server.core.i18n.components.label;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.core.i18n.components.label.authorization.permissions.TranslatorAdminPermission;
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
  @TranslatorAdminPermission
  public List<LabelEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<LabelEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveLabel")
  @TranslatorAdminPermission
  public LabelEntity saveOne(@GraphQLArgument(name = CrudApi.entity) LabelEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteLabels")
  @TranslatorAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteLabel")
  @TranslatorAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
