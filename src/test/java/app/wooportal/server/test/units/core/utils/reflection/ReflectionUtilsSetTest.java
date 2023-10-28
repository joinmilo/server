package app.wooportal.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsSetTest {
  
  @Test
  public void setOk() throws Exception {
    var test = new ReflectionTestEntity();
    var value = "test";
    
    ReflectionUtils.set("field", test, value);
    
    assertThat(test.getField()).isEqualTo(value);
  }
  
  @Test
  public void setParentOk() throws Exception {
    var test = new ReflectionTestEntity();
    var value = "test";
    
    ReflectionUtils.set("id", test, value);
    
    assertThat(test.getId()).isEqualTo(value);
  }
  
  @Test
  public void setNullValue() throws Exception {
    var test = new ReflectionTestEntity();
    
    ReflectionUtils.set("field", test, null);
    
    assertThat(test.getField()).isEqualTo(null);
  }
  
  @Test
  public void setNotExists() throws Exception {
    var test = new ReflectionTestEntity();
    
    ReflectionUtils.set("notExists", test, "test");
    
    assertThat(true).isTrue();
  }
  
  @Test
  public void setWithBadParams() throws Exception {
    var test = new ReflectionTestEntity();

    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.set("setNullObj", null, "test")));
    result.add(catchThrowable(() -> ReflectionUtils.set(null, test, "test")));
    result.add(catchThrowable(() -> ReflectionUtils.set("", test, "test")));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
  
}
