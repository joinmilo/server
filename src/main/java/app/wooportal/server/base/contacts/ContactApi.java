package app.wooportal.server.base.contacts;

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
public class ContactApi extends CrudApi<ContactEntity, ContactService> {

  public ContactApi(ContactService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getContacts")
  @ApprovedAndVerifiedPermission
  public PageableList<ContactEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContact")
  @ApprovedAndVerifiedPermission
  public Optional<ContactEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ContactEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContacts")
  @AdminPermission
  public List<ContactEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContactEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContact")
  public ContactEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ContactEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteContacts")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContact")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
