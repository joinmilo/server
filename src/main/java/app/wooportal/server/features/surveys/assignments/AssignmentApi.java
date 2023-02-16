package app.wooportal.server.features.surveys.assignments;

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
public class AssignmentApi extends CrudApi<AssignmentEntity, AssignmentService> {


  public AssignmentApi(AssignmentService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getAssignments")
  @ApprovedAndVerifiedPermission
  public PageableList<AssignmentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAssignment")
  @ApprovedAndVerifiedPermission
  public Optional<AssignmentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AssignmentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAssignments")
  @AdminPermission
  public List<AssignmentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AssignmentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAssignment")
  public AssignmentEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AssignmentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAssignments")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAssignment")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
