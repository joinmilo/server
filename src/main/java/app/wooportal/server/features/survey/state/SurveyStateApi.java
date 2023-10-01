package app.wooportal.server.features.survey.state;

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
public class SurveyStateApi extends CrudApi<SurveyStateEntity, SurveyStateService> {

  public SurveyStateApi(SurveyStateService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getSurveyStates")
  public PageableList<SurveyStateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSurveyState")
  public Optional<SurveyStateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyStateEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyStates")
  public List<SurveyStateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SurveyStateEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyState")
  public SurveyStateEntity saveOne(@GraphQLArgument(name = CrudApi.entity) SurveyStateEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyStates")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyState")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
