package app.milo.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetFieldTypeTest {
  
  @Test
  public void getFieldTypeForClassOk() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "field";
    
    var result = ReflectionUtils.getFieldType(test, fieldName);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).isEqualTo(String.class);
  }
  
  @Test
  public void getFieldTypeOk() throws Exception {
    var test = ReflectionTestEntity.class.getDeclaredField("field");
    
    var result = ReflectionUtils.getFieldType(test);
    
    assertThat(result).isEqualTo(String.class);
  }
  
  @Test
  public void getFieldTypeNotExist() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "fieldNotExists";
    
    var result = ReflectionUtils.getField(test, fieldName);
    
    assertThat(result.isEmpty()).isTrue();
  }
  
  @Test
  public void getFieldTypeWithBadParams() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "field";
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.getFieldType(null, fieldName)));
    result.add(catchThrowable(() -> ReflectionUtils.getFieldType(test, null)));
    result.add(catchThrowable(() -> ReflectionUtils.getFieldType(test, "")));
    result.add(catchThrowable(() -> ReflectionUtils.getFieldType(null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
