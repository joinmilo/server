package app.milo.server.test.units.core.dto.listing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.test.units.core.dto.listing.setup.CustomIterable;

public class PageableListIsEmptyTest {
  
  @Test
  public void isEmptyListFalse() {   
    var result = new PageableList<String>(new ArrayList<>(List.of("test", "test2")));
    
    assertThat(result.isEmpty()).isFalse();
  }
  
  @Test
  public void isEmptyPageFalse() {
    var result = new PageableList<String>(new PageImpl<String>(new ArrayList<String>(
        List.of("test", "test2"))));
    
    assertThat(result.isEmpty()).isFalse();
  }
  
  @Test
  public void isEmptyCustomIterableFalse() {
    var result = new PageableList<String>(new CustomIterable<String>(new ArrayList<String>(
        List.of("test", "test2"))));
    
    assertThat(result.isEmpty()).isFalse();
  }
  
  @Test
  public void isEmptyNull() {   
    var result = new PageableList<String>(null);
    
    assertThat(result.isEmpty()).isTrue();
  }
  
  @Test
  public void isEmptyList() {   
    var result = new PageableList<String>(new ArrayList<>());
    
    assertThat(result.isEmpty()).isTrue();
  }
  
  @Test
  public void isEmptyPage() {
    var result = new PageableList<String>(new PageImpl<String>(new ArrayList<String>()));
    
    assertThat(result.isEmpty()).isTrue();
  }
  
  @Test
  public void isEmptyCustomIterable() {
    var result = new PageableList<String>(new CustomIterable<String>());
    
    assertThat(result.isEmpty()).isTrue();
  }

}
