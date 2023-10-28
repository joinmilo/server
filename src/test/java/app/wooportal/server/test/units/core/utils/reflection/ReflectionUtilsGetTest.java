package app.wooportal.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetTest {
    
  @Test
  public void getOk() throws Exception {
    var test = new ReflectionTestEntity();
    var value = "test";
    test.setField(value);
    
    var result = ReflectionUtils.get("field", test);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).isEqualTo(value);
  }
  
  @Test
  public void getParentOk() throws Exception {
    var test = new ReflectionTestEntity();
    var value = "test";
    test.setId(value);
    
    var result = ReflectionUtils.get("id", test);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).isEqualTo(value);
  }
  
  @Test
  public void getNotExists() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.get("notExists", test);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getWithBadParams() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.get("test", null)));
    result.add(catchThrowable(() -> ReflectionUtils.get(null, test)));
    result.add(catchThrowable(() -> ReflectionUtils.get("", test)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
  
}
