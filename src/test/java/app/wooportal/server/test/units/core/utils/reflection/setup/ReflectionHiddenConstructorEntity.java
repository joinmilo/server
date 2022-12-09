package app.wooportal.server.test.units.core.utils.reflection.setup;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionHiddenConstructorEntity {

  private String field;
  
  public static ReflectionHiddenConstructorEntity getInstance() {
    return new ReflectionHiddenConstructorEntity();
  }
}
