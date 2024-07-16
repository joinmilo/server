package app.milo.server.test.units.core.utils.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import jakarta.persistence.OneToMany;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.test.units.core.utils.reflection.setup.ReflectionTestEntity;

public class ReflectionUtilsGetAnnotationValueTest {
  
  @Test
  public void getAnnotationValueTrue() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.getAnnotationValue(test, "mappedByField", OneToMany.class, "mappedBy");
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void getAnnotationValueEmpty() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.getAnnotationValue(test, "unMappedByField", OneToMany.class, "mappedBy");
    
    assertThat(result.isPresent()).isTrue();
    assertThat((String) result.get()).isBlank();
  }
  
  @Test
  public void getAnnotationValueAnnotationFieldNotExists() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = ReflectionUtils.getAnnotationValue(test, "mappedByField", OneToMany.class, "fieldNotExists");
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getAnnotationWithObjectBadParams() throws Exception {
    var test = new ReflectionTestEntity();
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotationValue(null, "mappedByField", OneToMany.class, "mappedBy")));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotationValue(test, null, OneToMany.class, "mappedBy")));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotationValue(test, "", OneToMany.class, "mappedBy")));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotationValue(test, "mappedByField", null, "mappedBy")));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotationValue(test, "mappedByField", OneToMany.class, null)));
    result.add(catchThrowable(() -> ReflectionUtils.getAnnotationValue(test, "mappedByField", OneToMany.class, "")));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
