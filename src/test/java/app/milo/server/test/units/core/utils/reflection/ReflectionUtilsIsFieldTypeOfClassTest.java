package app.milo.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsIsFieldTypeOfClassTest {
  
  @Test
  public void isFieldTypeOfClassTrue() throws Exception {
    var test = ReflectionTestEntity.class;
    
    var result = ReflectionUtils.isFieldTypeOfClass(test, "field", String.class);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void isFieldTypeOfClassFalse() throws Exception {
    var test = ReflectionTestEntity.class;
    
    var result = ReflectionUtils.isFieldTypeOfClass(test, "field", Integer.class);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void isFieldTypeOfClassNotExists() throws Exception {
    var test = ReflectionTestEntity.class;
    
    var result = ReflectionUtils.isFieldTypeOfClass(test, "isFieldNotExists", String.class);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void isFieldTypeOfClassWithBadParams() throws Exception {
    var test = ReflectionTestEntity.class;
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfClass(null, "field", String.class)));
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfClass(test, null, String.class)));
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfClass(test, "", String.class)));
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfClass(test, "field")));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
 
}
