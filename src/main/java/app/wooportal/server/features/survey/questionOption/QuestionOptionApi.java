package app.wooportal.server.features.survey.questionOption;

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
public class QuestionOptionApi extends CrudApi<QuestionOptionEntity, QuestionOptionService> {


  public QuestionOptionApi(QuestionOptionService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getQuestionOptions")
  public PageableList<QuestionOptionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getQuestionOption")
  public Optional<QuestionOptionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) QuestionOptionEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionOptions")
  @AdminPermission
  public List<QuestionOptionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<QuestionOptionEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionOption")
  public QuestionOptionEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) QuestionOptionEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionOptions")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionOption")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
