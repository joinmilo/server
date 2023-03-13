package app.wooportal.server.features.survey.questionType;

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
public class QuestionTypeApi extends CrudApi<QuestionTypeEntity, QuestionTypeService> {


  public QuestionTypeApi(QuestionTypeService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getQuestionTypes")
  public PageableList<QuestionTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getQuestionType")
  public Optional<QuestionTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) QuestionTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionTypes")
  @AdminPermission
  public List<QuestionTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<QuestionTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionType")
  public QuestionTypeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) QuestionTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionTypes")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionType")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
