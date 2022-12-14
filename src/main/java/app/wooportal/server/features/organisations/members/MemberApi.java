package app.wooportal.server.features.organisations.members;

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
public class MemberApi extends CrudApi<MemberEntity, MemberService> {


  public MemberApi(MemberService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getMembers")
  @ApprovedAndVerifiedPermission
  public PageableList<MemberEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMember")
  @ApprovedAndVerifiedPermission
  public Optional<MemberEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MemberEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMembers")
  @AdminPermission
  public List<MemberEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MemberEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMember")
  public MemberEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MemberEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMembers")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMember")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
