package app.milo.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.PersistenceUtils;
import app.milo.server.test.units.core.utils.persistence.setup.PersistenceValidityEntity;

public class PersistenceUtilsIsNullableTest {
  
  @Test
  public void isColumnNullableField() throws Exception {
    var test = new PersistenceValidityEntity();
    var nullableField = "nullableField";
 
    var result = PersistenceUtils.isNullable(test, nullableField);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isNullableNonAnnotatedField() throws Exception {
    var test = new PersistenceValidityEntity();
    var nullableField = "validField";
 
    var result = PersistenceUtils.isNullable(test, nullableField);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isJoinColumnNullableField() throws Exception {
    var test = new PersistenceValidityEntity();
    var nullableField = "nullableJoinField";
 
    var result = PersistenceUtils.isNullable(test, nullableField);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isNullableFieldNotExists() throws Exception {
    var testEntity = new PersistenceValidityEntity();
    var nullableField = "notExists";
 
    var result = PersistenceUtils.isNullable(testEntity, nullableField);

    assertThat(result).isTrue();
  }
  
  @Test
  public void isNullableFieldNullObj() throws Exception {
    var result = PersistenceUtils.isNullable(null, "field");

    assertThat(result).isTrue();
  }
  
  @Test
  public void isNotJoinColumnNullableField() throws Exception {
    var test = new PersistenceValidityEntity();
    var notNullableField = "notNullableJoinField";
 
    var result = PersistenceUtils.isNullable(test, notNullableField);

    assertThat(result).isFalse();
  }
  
  @Test
  public void isNotNullableField() throws Exception {
    var test = new PersistenceValidityEntity();
    var notNullableField = "notNullableField";
 
    var result = PersistenceUtils.isNullable(test, notNullableField);

    assertThat(result).isFalse();
  }
  
  @Test
  public void isNotNullableIdField() throws Exception {
    var test = new PersistenceValidityEntity();
    var idField = "id";
 
    var result = PersistenceUtils.isNullable(test, idField);

    assertThat(result).isFalse();
  }


}
