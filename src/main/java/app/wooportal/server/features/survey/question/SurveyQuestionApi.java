package app.wooportal.server.features.survey.question;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SurveyQuestionApi extends CrudApi<SurveyQuestionEntity, SurveyQuestionService> {

  public SurveyQuestionApi(SurveyQuestionService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getSurveyQuestions")
  public PageableList<SurveyQuestionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSurveyQuestion")
  public Optional<SurveyQuestionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyQuestionEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyQuestions")
  @AdminPermission
  public List<SurveyQuestionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SurveyQuestionEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyQuestion")
  public SurveyQuestionEntity saveOne(@GraphQLArgument(name = CrudApi.entity) SurveyQuestionEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyQuestions")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyQuestion")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
