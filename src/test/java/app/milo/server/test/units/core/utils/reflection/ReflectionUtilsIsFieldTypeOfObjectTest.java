package app.milo.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsIsFieldTypeOfObjectTest {
  
  @Test
  public void isFieldTypeOfWithObjTrue() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.isFieldTypeOfObject(test, "field", String.class);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void isFieldTypeOfObjectFalse() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.isFieldTypeOfObject(test, "field", Integer.class);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void isFieldTypeOfObjectNotExists() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.isFieldTypeOfObject(test, "isFieldNotExists", String.class);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void isFieldTypeOfObjectWithBadParams() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfObject(null, "field", String.class)));
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfObject(test, null, String.class)));
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfObject(test, "", String.class)));
    result.add(catchThrowable(() -> ReflectionUtils.isFieldTypeOfObject(test, "field")));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
 
}
