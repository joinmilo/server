package app.milo.server.base.userContext.security;

import java.io.Serial;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.security.components.user.UserEntity;
import app.milo.server.core.security.dto.UserDetails;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class UserContextDetails extends UserDetails {

  @Serial
  private static final long serialVersionUID = 1L;

  private UserContextEntity userContext;

  public UserContextDetails(
      UserContextEntity userContext) {
    super(userContext.getUser().getEmail(), userContext.getUser().getPassword(), userContext.getUser().getRoles().stream()   
        .map(r -> r.getPrivileges())
        .flatMap(Collection::stream)
        .map(privilege -> privilege.getCode())
        .distinct()
        .map(code -> new SimpleGrantedAuthority(code))
        .collect(Collectors.toList()));
    
    this.userContext = userContext;
  }
  
  public UserEntity getUser() {
    return userContext.getUser();
  }
  
  public boolean isVerified() {
    return userContext.getUser().getVerified();
  }
  
}
