package app.wooportal.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import javax.persistence.Transient;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetAnnotationTest {
  
  @Test
  public void getAnnotationWithFieldTrue() throws Exception {
    var test = new ReflectionTestEntity().getClass().getDeclaredField("transientField");
    
    var result = ReflectionUtils.getAnnotation(test, Transient.class);
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void getAnnotationWithObjectTrue() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.getAnnotation(test, "transientField", Transient.class);
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void getAnnotationWithFieldFalse() throws Exception {
    var test = new ReflectionTestEntity().getClass().getDeclaredField("field");
    
    var result = ReflectionUtils.getAnnotation(test, Transient.class);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getAnnotationWithObjectFalse() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.getAnnotation(test, "field", Transient.class);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getAnnotationWithObjectFieldNotExistsFalse() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.getAnnotation(test, "fieldNotExists", Transient.class);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getAnnotationWithFieldBadParams() throws Exception {
    var test = new ReflectionTestEntity().getClass().getDeclaredField("transientField");
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotation(test, null)));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotation(null, Transient.class)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
  
  @Test
  public void getAnnotationWithObjectBadParams() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotation(null, "transientField", Transient.class)));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotation(test, null, Transient.class)));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotation(test, "", Transient.class)));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotation(test, "transientField", null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
