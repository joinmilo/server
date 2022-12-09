package app.wooportal.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.utils.PersistenceUtils;
import app.wooportal.server.test.units.core.utils.persistence.setup.PersistenceEqualityEntity;
import app.wooportal.server.test.units.core.utils.persistence.setup.PersistenceValidityEntity;

public class PersistenceUtilsUniqueClassConstraintsEqualTest {
  
  @Test
  public void uniqueClassConstraintEqualFieldTrue() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setField("test");
    var target = new PersistenceEqualityEntity();
    target.setField("test");
    var field = source.getClass().getDeclaredField("field");
    var constraints = List.of("field");
 
    var result = PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, target);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void uniqueClassConstraintEqualFieldNullTrue() throws Exception {
    var source = new PersistenceEqualityEntity();
    var target = new PersistenceEqualityEntity();
    var field = source.getClass().getDeclaredField("field");
    var constraints = List.of("field");
 
    var result = PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, target);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void uniqueClassConstraintEqualJoinTrue() throws Exception {
    var child = new PersistenceValidityEntity();
    child.setId("test");
    
    var source = new PersistenceEqualityEntity();
    source.setNamedEntity(child);
    
    var target = new PersistenceEqualityEntity();
    target.setNamedEntity(child);
    
    var field = source.getClass().getDeclaredField("namedEntity");
    var constraints = List.of("entity");
 
    var result = PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, target);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void uniqueClassConstraintEqualJoinHeuristicallyTrue() throws Exception {
    var child = new PersistenceValidityEntity();
    child.setId("test");
    
    var source = new PersistenceEqualityEntity();
    source.setUnNamedEntity(child);
    
    var target = new PersistenceEqualityEntity();
    target.setUnNamedEntity(child);
    
    var field = source.getClass().getDeclaredField("unNamedEntity");
    var constraints = List.of("un_named_entity_id");
 
    var result = PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, target);
    
    assertThat(result).isTrue();
  }
  
  @Test
  public void uniqueClassConstraintEqualNoMatch() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setField("test");
    var target = new PersistenceEqualityEntity();
    target.setField("test1");
    var field = source.getClass().getDeclaredField("field");
    var constraints = List.of("field");
 
    var result = PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, target);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void uniqueClassConstraintEqualFieldFalse() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setNotUniqueClass("test");
    var target = new PersistenceEqualityEntity();
    target.setNotUniqueClass("test");
    var field = source.getClass().getDeclaredField("notUniqueClass");
    var constraints = List.of("field");
 
    var result = PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, target);
    
    assertThat(result).isFalse();
  }
  
  @Test
  public void uniqueClassConstraintEqualWithBadParams() throws Exception {
    var source = new PersistenceEqualityEntity();
    source.setUniqueColumn("test");
    var target = new PersistenceEqualityEntity();
    target.setUniqueColumn("test");
    var field = source.getClass().getDeclaredField("uniqueColumn");
    var constraints = List.of("field");
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> PersistenceUtils.uniqueClassConstraintEqual(constraints, null, source, target)));
    result.add(catchThrowable(() -> PersistenceUtils.uniqueClassConstraintEqual(constraints, field, null, target)));
    result.add(catchThrowable(() -> PersistenceUtils.uniqueClassConstraintEqual(constraints, field, source, null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }

}
