package app.milo.server.core.i18n.components.language;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class LanguageApi extends CrudApi<LanguageEntity, LanguageService> {

  public LanguageApi(LanguageService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getLanguages")
  public PageableList<LanguageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getLanguage")
  public Optional<LanguageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) LanguageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveLanguages")
  public List<LanguageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<LanguageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveLanguage")
  public LanguageEntity saveOne(@GraphQLArgument(name = CrudApi.entity) LanguageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteLanguages")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteLanguage")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
