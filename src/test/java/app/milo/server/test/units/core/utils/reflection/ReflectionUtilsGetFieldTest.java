package app.milo.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetFieldTest {
  
  @Test
  public void getFieldOk() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "field";
    
    var result = ReflectionUtils.getField(test, fieldName);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).is(
        new Condition<Field>(field -> field.getName().equals(fieldName), "field exists"));
  }
  
  @Test
  public void getFieldNotExist() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "fieldNotExists";
    
    var result = ReflectionUtils.getField(test, fieldName);
    
    assertThat(result.isEmpty()).isTrue();
  }
  
  @Test
  public void getFieldWithBadParams() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "field";
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.getField(null, fieldName)));
    result.add(catchThrowable(() -> ReflectionUtils.getField(test, null)));
    result.add(catchThrowable(() -> ReflectionUtils.getField(test, "")));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
