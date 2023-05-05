package app.wooportal.server.core.security.components.user;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.push.MessageDto;
import app.wooportal.server.core.push.NotificationType;
import app.wooportal.server.core.push.PushService;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class UserApi extends CrudApi<UserEntity, UserService> {

  private final PushService pushService;

  public UserApi(UserService userService, PushService pushService) {
    super(userService);

    this.pushService = pushService;
  }

  @Override
  @GraphQLQuery(name = "getUsers")
  public PageableList<UserEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getUser")
  public Optional<UserEntity> readOne(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.readOne(entity);
  }

  @GraphQLQuery(name = "me")
  public Optional<UserEntity> me() {
    return service.me();
  }

  @Override
  @GraphQLMutation(name = "saveUsers")
  @AdminPermission
  public List<UserEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveUser")
  public UserEntity saveOne(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.saveOne(entity);
  }

  @GraphQLMutation(name = "saveMe")
  public Optional<UserEntity> saveMe(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return service.saveMe(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteUsers")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteUser")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

  @GraphQLMutation(name = "deleteMe")
  public Boolean deleteMe(String password) {
    var deletedUser = service.deleteMe(password);

    if (deletedUser.isPresent()) {
      var message = new MessageDto(
          "Benutzer gelöscht",
          "Benutzer mit dem Namen: "
              + deletedUser.get().getFirstName() + " " 
              + deletedUser.get().getFirstName() + " " 
              + "hat soeben das Benutzerkonto gelöscht",
          NotificationType.deletedUser);

      pushService.sendPush(
          service.readAll(service.collectionQuery(service.getPredicate().withRole(RoleService.admin)))
              .getList(),
          message);
      return true;
    }
    return false;
  }

  @GraphQLMutation(name = "addUploads")
  public Optional<UserEntity> addUploads(List<MediaEntity> uploads) {
    return service.addUploads(uploads);
  }

  @GraphQLMutation(name = "deleteUploads")
  public Optional<UserEntity> deleteUpload(List<String> uploadIds) {
    return service.deleteUpload(uploadIds);
  }

  @GraphQLMutation(name = "sendPasswordReset")
  public Boolean forgetPassword(String email) {
    if (email == null || email.isBlank()) {
      throw new BadParamsException("Mail address is null or empty");
    }
    return service.createPasswordReset(email);
  }

  @GraphQLMutation(name = "resetPassword")
  public Boolean resetPassword(String token, String password) {
    if (token == null || token.isBlank() || password == null || password.isBlank()) {
      throw new BadParamsException("key or password are null or empty");
    }
    return service.resetPassword(token, password);
  }

  @GraphQLMutation(name = "sendVerification")
  public Boolean sendVerification(String email) {
    if (email == null || email.isBlank()) {
      throw new BadParamsException("Mail address is null or empty");
    }
    return service.createVerification(email);
  }

  @GraphQLMutation(name = "verify")
  public UserEntity verify(String token) {
    if (token == null || token.isBlank()) {
      throw new BadParamsException("key is null or empty");
    }
    return service.verify(token);
  }
  
  @GraphQLMutation(name = "changePassword")
  public Boolean changePassword(String newPassword) {
    if (newPassword == null || newPassword.isBlank()) {
      throw new BadParamsException("New password is null or empty");
    }
    return service.changePassword(newPassword);
  }
  
  @GraphQLMutation(name = "checkPassword")
  public Double checkPassword(String password) {
    return password != null
        ? service.calculatePasswordEntropy(password)
        : 0;
  }
}
