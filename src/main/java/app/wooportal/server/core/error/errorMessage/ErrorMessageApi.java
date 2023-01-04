package app.wooportal.server.core.error.errorMessage;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.ErrorMailService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class ErrorMessageApi extends CrudApi<ErrorMessageEntity, ErrorMessageService> {

  public ErrorMessageApi(ErrorMessageService service, ErrorMailService errorMailService) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getErrorMessages")
  public PageableList<ErrorMessageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getErrorMessage")
  public Optional<ErrorMessageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ErrorMessageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveErrorMessages")
  public List<ErrorMessageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ErrorMessageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveErrorMessage")
  public ErrorMessageEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ErrorMessageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteErrorMessages")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteErrorMessage")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

  @GraphQLMutation(name = "sendError")
  public Boolean sendError(String stackTrace) throws Throwable {
    return service.sendErrorMail(stackTrace);
  }

}
