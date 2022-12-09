package app.wooportal.server.core.security.components.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
  
  private String access;
  
  private String refresh;

}
