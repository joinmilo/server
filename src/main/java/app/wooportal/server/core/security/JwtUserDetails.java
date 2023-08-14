package app.wooportal.server.core.security;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import app.wooportal.server.core.security.components.role.base.RoleEntity;
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

  public JwtUserDetails(
      UserEntity user,
      List<RoleEntity> roles) {
    super(user.getEmail(), user.getPassword(), roles.stream()   
        .map(r -> r.getPrivileges())
        .flatMap(Collection::stream)
        .map(privilege -> privilege.getCode())
        .distinct()
        .map(code -> new SimpleGrantedAuthority(code))
        .collect(Collectors.toList()));
    
    this.user = user;
  }
  
  public boolean isVerified() {
    return user.getVerified();
  }
  
}
