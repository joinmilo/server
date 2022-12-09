package app.wooportal.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.utils.PersistenceUtils;
import app.wooportal.server.test.units.core.utils.persistence.setup.PersistenceValidityEntity;

public class PersistenceUtilsIsValidEntityTest {
  
  @Test
  public void isValidEntity() throws Exception {
    var test = new PersistenceValidityEntity();
 
    var result = PersistenceUtils.isValidEntity(test);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isNullEntity() throws Exception {  
    var result = PersistenceUtils.isValidEntity(null);

    assertThat(result).isFalse();
  }
  
  @Test
  public void isWrongEntity() throws Exception {
    var test = new Object();
    
    var result = PersistenceUtils.isValidEntity(test);

    assertThat(result).isFalse();
  }
}
