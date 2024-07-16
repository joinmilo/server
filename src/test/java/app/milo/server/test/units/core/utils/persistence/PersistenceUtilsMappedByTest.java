package app.milo.server.test.units.core.utils.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import org.junit.jupiter.api.Test;
import app.milo.server.core.utils.PersistenceUtils;
import app.milo.server.test.units.core.utils.persistence.setup.PersistenceEqualityEntity;

public class PersistenceUtilsMappedByTest {
  
  @Test
  public void mappedByTrue() throws Exception {
    var test = new PersistenceEqualityEntity();
 
    var result = PersistenceUtils.mappedBy(test, "mappedOneToOne");
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).isEqualTo("test");
  }
  
  @Test
  public void mappedByFalse() throws Exception {
    var test = new PersistenceEqualityEntity();
    
    var result = PersistenceUtils.mappedBy(test, "field");
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void mappedByEmpty() throws Exception {
    var test = new PersistenceEqualityEntity();
 
    var result = PersistenceUtils.mappedBy(test, "unmappedOneToOne");
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void mappedByOneToOne() throws Exception {
    var test = new PersistenceEqualityEntity();
 
    var result = PersistenceUtils.mappedBy(test, "mappedOneToOne", OneToOne.class);
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void mappedByOneToMany() throws Exception {
    var test = new PersistenceEqualityEntity();
 
    var result = PersistenceUtils.mappedBy(test, "mappedOneToMany", OneToMany.class);
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void mappedByWithBadParams() throws Exception {
    var test = new PersistenceEqualityEntity();
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> PersistenceUtils.mappedBy(null, "mappedOneToOne")));
    result.add(catchThrowable(() -> PersistenceUtils.mappedBy(test, null)));
    result.add(catchThrowable(() -> PersistenceUtils.mappedBy(test, "")));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }

}
