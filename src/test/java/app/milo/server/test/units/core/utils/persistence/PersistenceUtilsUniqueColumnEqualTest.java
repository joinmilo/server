package app.milo.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.PersistenceUtils;
import app.milo.server.test.units.core.utils.persistence.setup.PersistenceEqualityEntity;

public class PersistenceUtilsUniqueColumnEqualTest {
  
  @Test
  public void uniqueColumnEqualTrue() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setUniqueColumn("test");
    var target = new PersistenceEqualityEntity();
    target.setUniqueColumn("test");
    var field = source.getClass().getDeclaredField("uniqueColumn");
 
    var result = PersistenceUtils.uniqueColumnEqual(field, source, target);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void uniqueColumnEqualFalse() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setUniqueColumn("test");
    var target = new PersistenceEqualityEntity();
    target.setUniqueColumn("uniqueColumnEqualFalse");
    var field = source.getClass().getDeclaredField("uniqueColumn");
 
    var result = PersistenceUtils.uniqueColumnEqual(field, source, target);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void uniqueColumnEqualNullValue() throws Exception {
    var source = new PersistenceEqualityEntity();
    var target = new PersistenceEqualityEntity();
    var field = source.getClass().getDeclaredField("uniqueColumn");
 
    var result = PersistenceUtils.uniqueColumnEqual(field, source, target);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void uniqueColumnNotUnique() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setNotUniqueColumn("test");
    var target = new PersistenceEqualityEntity();
    target.setNotUniqueColumn("test");
    var field = source.getClass().getDeclaredField("notUniqueColumn");
 
    var result = PersistenceUtils.uniqueColumnEqual(field, source, target);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void uniqueColumnNoAnnotation() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setField("test");
    var target = new PersistenceEqualityEntity();
    target.setField("test");
    var field = source.getClass().getDeclaredField("field");
 
    var result = PersistenceUtils.uniqueColumnEqual(field, source, target);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void uniqueColumnEqualWithBadParams() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setUniqueColumn("test");
    var target = new PersistenceEqualityEntity();
    target.setUniqueColumn("test");
    var field = source.getClass().getDeclaredField("uniqueColumn");
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> PersistenceUtils.uniqueColumnEqual(null, source, target)));
    result.add(catchThrowable(() -> PersistenceUtils.uniqueColumnEqual(field, null, target)));
    result.add(catchThrowable(() -> PersistenceUtils.uniqueColumnEqual(field, source, null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }

}
