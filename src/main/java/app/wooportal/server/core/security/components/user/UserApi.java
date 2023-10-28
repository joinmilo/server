package app.wooportal.server.core.security.components.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.userDeletion.base.UserDeletionEntity;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.security.components.user.authorization.permissions.UserAdminPermission;
import app.wooportal.server.core.security.permissions.Authenticated;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class UserApi extends CrudApi<UserEntity, UserService> {

  public UserApi(UserService userService) {
    super(userService);
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

  @Override
  @GraphQLMutation(name = "saveUsers")
  @UserAdminPermission
  public List<UserEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveUser")
  @UserAdminPermission
  public UserEntity saveOne(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteUsers")
  @UserAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteUser")
  @UserAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

  @GraphQLMutation(name = "deleteMe")
  @Authenticated
  public Boolean deleteMe(String password, UserDeletionEntity userDeletion ) {
    return service.deleteMe(password, userDeletion);
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
      throw new BadParamsException("token is null or empty");
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
    return password != null ? service.calculatePasswordEntropy(password) : 0;
  }
}
