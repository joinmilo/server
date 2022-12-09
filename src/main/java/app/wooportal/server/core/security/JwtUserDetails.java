package app.wooportal.server.core.security;

import java.io.Serial;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import app.wooportal.server.core.security.components.role.RoleEntity;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.components.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class JwtUserDetails extends User {

  @Serial
  private static final long serialVersionUID = 1L;

  private UserEntity user;
  
  private List<RoleEntity> roles;

  public JwtUserDetails(
      UserEntity user,
      List<RoleEntity> roles) {
    super(user.getEmail(), user.getPassword(), Collections.emptyList());
    this.user = user;
    this.roles = roles;
  }
  
  public String[] getRoles() {
    return roles.stream().map(RoleEntity::getKey).toArray(String[]::new);
  }

  public boolean isAdmin() {
    return roles.stream().anyMatch(role -> role.getKey().equalsIgnoreCase(RoleService.admin));
  }
  
  public boolean isApproved() {
    return user.getApproved();
  }
  
  public boolean isVerified() {
    return user.getVerified();
  }
  
}
