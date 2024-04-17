package app.milo.server.test.units.core.dto.listing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import app.milo.server.core.base.dto.listing.PageableList;

public class PageableListGetTest {
  
  @Test
  public void getOk() {
    var element1 = "test";
    var element2 = "test2";
    var element3 = "test3";
    var list = new ArrayList<>(List.of(element1, element2, element3));
    
    var result = new PageableList<String>(list);
    
    assertThat(result.get(2)).isEqualTo(element3);
  }
  
  @Test
  public void getOutOfBound() {
    var element1 = "test";
    var list = new ArrayList<>(List.of(element1));
    
    var result = new PageableList<String>(list);
    
    assertThat(result.get(1)).isNull();
  }
  
  @Test
  public void getNull() {   
    var result = new PageableList<String>(null);
    
    assertThat(result.get(0)).isNull();
  }
  
  @Test
  public void getEmpty() {   
    var result = new PageableList<String>(new ArrayList<>());
    
    assertThat(result.get(0)).isNull();
  }

}
