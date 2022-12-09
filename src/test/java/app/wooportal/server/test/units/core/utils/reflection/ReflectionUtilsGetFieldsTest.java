package app.wooportal.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Field;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetFieldsTest {
  
  @Test
  public void getFieldsOk() throws Exception {
    var test = ReflectionTestEntity.class;
    var classField = "field";
    var parentClassField = "id";
    
    var result = ReflectionUtils.getFields(test);
    
    assertThat(result).areExactly(1, 
        new Condition<Field>(field -> field.getName().equals(classField), "has own field"));
    
    assertThat(result).areExactly(1, 
        new Condition<Field>(field -> field.getName().equals(parentClassField), "has parent field"));
  }
  
  @Test
  public void getFieldsNullObj() throws Exception {
    var result = ReflectionUtils.getFields(null);
    
    assertThat(result).isEmpty();
  }
  
}
