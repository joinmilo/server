package app.wooportal.server.features.survey.questionOption;

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
public class SurveyQuestionOptionApi extends CrudApi<SurveyQuestionOptionEntity, SurveyQuestionOptionService> {


  public SurveyQuestionOptionApi(SurveyQuestionOptionService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getQuestionOptions")
  public PageableList<SurveyQuestionOptionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getQuestionOption")
  public Optional<SurveyQuestionOptionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyQuestionOptionEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionOptions")
  public List<SurveyQuestionOptionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SurveyQuestionOptionEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionOption")
  public SurveyQuestionOptionEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyQuestionOptionEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionOptions")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionOption")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
