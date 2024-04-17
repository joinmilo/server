package app.milo.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.PersistenceUtils;
import app.milo.server.test.units.core.utils.persistence.setup.PersistenceEqualityEntity;
import app.milo.server.test.units.core.utils.persistence.setup.PersistenceValidityEntity;

public class PersistenceUtilsClassUniqueConstraintsTest {
  
  @Test
  public void getClassUniqueConstraintFields() throws Exception {
    var test = new PersistenceEqualityEntity();
 
    var result = PersistenceUtils.getClassUniqueConstraintFields(test);
    
    assertThat(result).contains("entity_id", "field");
  }
  
  @Test
  public void getClassUniqueConstraintFieldsNoConstraint() throws Exception {
    var test = new PersistenceValidityEntity();
 
    var result = PersistenceUtils.getClassUniqueConstraintFields(test);
    
    assertThat(result).isNullOrEmpty();
  }
  
  @Test
  public void getClassUniqueConstraintFieldsNoTable() throws Exception {
    var test = new Object();
 
    var result = PersistenceUtils.getClassUniqueConstraintFields(test);
    
    assertThat(result).isNullOrEmpty();
  }
  
  @Test
  public void getClassUniqueConstraintNoneFields() throws Exception {
    var test = new PersistenceEqualityEntity().new NullUniqueConstraints();
 
    var result = PersistenceUtils.getClassUniqueConstraintFields(test);
    
    assertThat(result).isNullOrEmpty();
  }
  
  @Test
  public void getClassUniqueConstraintFieldsNullObject() throws Exception {
    var result = catchThrowable(() -> PersistenceUtils.getClassUniqueConstraintFields(null));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
  
}
