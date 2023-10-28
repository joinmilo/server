package app.wooportal.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionHiddenConstructorEntity;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsNewInstanceTest {
  
  @Test
  public void newInstanceOk() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.newInstance(test);
    
    assertThat(result).isOfAnyClassIn(test.getClass());
  }
  
  @Test
  public void newInstanceNullObj() throws Exception {
    var result = catchThrowable(() -> ReflectionUtils.newInstance(null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  public void newInstancePrimitive() throws Exception {
    int test = 5;
    
    var result = ReflectionUtils.newInstance(test);
    
    assertThat(result).isOfAnyClassIn(Integer.class);
    assertThat(result).isEqualTo(test);
    
    test++;
    assertThat(result).isNotEqualTo(test);
  }
  
  @Test
  public void newInstanceHiddenConstructor() throws Exception {
    var test = ReflectionHiddenConstructorEntity.getInstance();
    
    var result = ReflectionUtils.newInstance(test);
    
    assertThat(result).isEqualTo(test);
  }
  
}
