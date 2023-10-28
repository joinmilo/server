package app.wooportal.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.PersistenceUtils;

public class PersistenceUtilsIsValidCollectionTest {
  
  @Test
  public void isValidCollection() throws Exception {
    var test = new ArrayList<String>();
 
    var result = PersistenceUtils.isValidCollection(test);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isNullEntity() throws Exception {  
    var result = PersistenceUtils.isValidCollection(null);

    assertThat(result).isFalse();
  }
  
  @Test
  public void isWrongEntity() throws Exception {
    var test = new Object();
    
    var result = PersistenceUtils.isValidCollection(test);

    assertThat(result).isFalse();
  }
}
