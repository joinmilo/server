package app.wooportal.server.features.surveys.surveyResult;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SurveyResultApi extends CrudApi<SurveyResultEntity, SurveyResultService> {


  public SurveyResultApi(SurveyResultService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getSurveyResults")
  @ApprovedAndVerifiedPermission
  public PageableList<SurveyResultEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSurveyResult")
  @ApprovedAndVerifiedPermission
  public Optional<SurveyResultEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyResultEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyResults")
  @AdminPermission
  public List<SurveyResultEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SurveyResultEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyResult")
  public SurveyResultEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyResultEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyResults")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyResult")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
