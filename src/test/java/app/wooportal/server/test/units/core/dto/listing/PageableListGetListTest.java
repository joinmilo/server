package app.wooportal.server.test.units.core.dto.listing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.test.units.core.dto.listing.setup.CustomIterable;

public class PageableListGetListTest {
  
  @Test
  public void getList() {
    var list = new ArrayList<>(List.of("test", "test2"));
    
    var result = new PageableList<String>(list);
    
    assertThat(result.getList()).containsAll(list);
  }
  
  @Test
  public void getListOnPage() {
    var list = new ArrayList<String>(List.of("test", "test2"));
    
    var result = new PageableList<String>(new PageImpl<String>(list));
    
    assertThat(result.getList()).containsAll(list);
  }
  
  @Test
  public void getListEmpty() {   
    var result = new PageableList<String>(new ArrayList<>());
    
    assertThat(result.getList().isEmpty()).isTrue();
  }
  
  @Test
  public void getListUnsupportedType() {
    var list = new PageableList<String>(new CustomIterable<String>(
        List.of("test", "test2")));
    
    var result = catchThrowable(() -> list.getList());
    
    assertThat(result).isInstanceOfAny(UnsupportedOperationException.class);
  }
  
}
