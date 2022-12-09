package app.wooportal.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.utils.PersistenceUtils;
import app.wooportal.server.test.units.core.utils.persistence.setup.PersistenceValidityEntity;

public class PersistenceUtilsIsValidFieldTest {
  
  @Test
  public void isValidField() throws Exception {
    var test = new PersistenceValidityEntity().getClass().getDeclaredField("validField");
 
    var result = PersistenceUtils.isValidField(test);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isValidFieldIgnoredField() throws Exception {
    var serialVersionUidField = new PersistenceValidityEntity().getClass().getDeclaredField("serialVersionUID");
 
    var result = PersistenceUtils.isValidField(serialVersionUidField);

    assertThat(result).isFalse();
  }
  
  @Test
  public void isValidFieldTransientField() throws Exception {
    var transientField = new PersistenceValidityEntity().getClass().getDeclaredField("transientField");
 
    var result = PersistenceUtils.isValidField(transientField);

    assertThat(result).isFalse();
  }
  
  @Test
  public void isValidFieldNullField() throws Exception { 
    var result = PersistenceUtils.isValidField(null);

    assertThat(result).isFalse();
  }

}
