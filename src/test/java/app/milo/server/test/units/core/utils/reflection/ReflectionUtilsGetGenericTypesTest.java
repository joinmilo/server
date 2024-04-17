package app.milo.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;
import app.milo.server.test.units.core.utils.reflection.setup.TestDataService;

public class ReflectionUtilsGetGenericTypesTest {
  
  @Test
  public void getGenericTypesOk() throws Exception {
    var testClass = TestDataService.class;
    
    var result = ReflectionUtils.getGenericTypes(testClass);
    
    assertThat(result).anyMatch(c -> 
      c.getTypeName().equals(ReflectionTestEntity.class.getTypeName()));
  }
  
  @Test
  public void getGenericTypesNoGenericType() throws Exception {
    var result = ReflectionUtils.getGenericTypes(Object.class);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void getGenericTypesNullObj() throws Exception {   
    var result = ReflectionUtils.getGenericTypes(null);
    
    assertThat(result).isNull();
  }
  
}
