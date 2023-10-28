package app.wooportal.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.PersistenceUtils;
import app.wooportal.server.test.units.core.utils.persistence.setup.PersistenceEqualityEntity;

public class PersistenceUtilsIdsEqualTest {
  
  @Test
  public void idsEqual() throws Exception {
    var obj1 = new PersistenceEqualityEntity();
    obj1.setId("test");
    var obj2 = new PersistenceEqualityEntity();
    obj2.setId("test");
 
    var result = PersistenceUtils.idsEqual(obj1, obj2);

    assertThat(result).isTrue();
  }
  
  @Test
  public void idsNotEqual() throws Exception {
    var obj1 = new PersistenceEqualityEntity();
    obj1.setId("test1");
    var obj2 = new PersistenceEqualityEntity();
    obj2.setId("test");
 
    var result = PersistenceUtils.idsEqual(obj1, obj2);

    assertThat(result).isFalse();
  }
  
  @Test
  public void idsEqualNullParams() throws Exception {
    var obj1 = new PersistenceEqualityEntity();
    obj1.setId("test1");
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> PersistenceUtils.idsEqual(obj1, null)));
    result.add(catchThrowable(() -> PersistenceUtils.idsEqual(null, obj1)));
    result.add(catchThrowable(() -> PersistenceUtils.idsEqual(null, null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
  
  @Test
  public void idFieldNull() throws Exception {
    var obj1 = new PersistenceEqualityEntity();
    var obj2 = new PersistenceEqualityEntity();
 
    var result = PersistenceUtils.idsEqual(obj1, obj2);

    assertThat(result).isFalse();
  }

}
