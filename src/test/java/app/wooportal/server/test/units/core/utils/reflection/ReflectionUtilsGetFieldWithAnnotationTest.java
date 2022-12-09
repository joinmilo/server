package app.wooportal.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetFieldWithAnnotationTest {
  
  @Test
  public void getFieldWithAnnotationsOk() throws Exception {
    var test = ReflectionTestEntity.class;
    var fieldName = "field";
    
    var result = ReflectionUtils.getFieldsWithAnnotation(test, OneToMany.class);
    
    assertThat(result).areAtMost(2, 
        new Condition<Field>(field -> field.getName().equals(fieldName), "fields exists"));
  }
  
  @Test
  public void getFieldWithAnnotationsBadParams() throws Exception {
    var test = ReflectionTestEntity.class;
    var annotation = OneToMany.class;
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.getFieldsWithAnnotation(null, annotation)));
    result.add(catchThrowable(() -> ReflectionUtils.getFieldsWithAnnotation(test, null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
