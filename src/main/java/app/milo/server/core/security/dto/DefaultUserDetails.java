package app.milo.server.core.security.dto;

import java.io.Serial;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import app.milo.server.core.security.components.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class DefaultUserDetails extends UserDetails {

  @Serial
  private static final long serialVersionUID = 1L;

  private UserEntity user;

  public DefaultUserDetails(
      UserEntity user) {
    super(user.getEmail(), user.getPassword(), user.getRoles().stream()   
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
