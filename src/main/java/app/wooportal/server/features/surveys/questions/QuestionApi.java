package app.wooportal.server.features.surveys.questions;

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
public class QuestionApi extends CrudApi<QuestionEntity, QuestionService> {


  public QuestionApi(QuestionService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getQuestions")
  @ApprovedAndVerifiedPermission
  public PageableList<QuestionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getQuestion")
  @ApprovedAndVerifiedPermission
  public Optional<QuestionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) QuestionEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveQuestions")
  @AdminPermission
  public List<QuestionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<QuestionEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveQuestion")
  public QuestionEntity saveOne(@GraphQLArgument(name = CrudApi.entity) QuestionEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestions")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestion")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
