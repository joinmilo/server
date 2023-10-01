package app.wooportal.server.core.security.dto;

import java.io.Serial;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import app.wooportal.server.core.security.components.user.UserEntity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class UserDetails extends User {

  @Serial
  private static final long serialVersionUID = 1L;

  public UserDetails(
      String username, String password, List<GrantedAuthority> privileges) {
    super(username, password, privileges);
  }
  
  public abstract boolean isVerified();
  
  public abstract UserEntity getUser();
  
  
}
