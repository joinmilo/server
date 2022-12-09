package app.wooportal.server.test.units.core.dto.listing;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import app.wooportal.server.core.base.dto.listing.PageableList;

public class PageableListSetResultTest {
  
  @Test
  public void setResult() {
    var list = new ArrayList<>(List.of("test", "test2"));
    var pageable = new PageableList<String>();
    
    pageable.setResult(list);
    
    assertThat(pageable.getTotal()).isEqualTo(list.size());
  }
  
  @Test
  public void getListOnPage() {
    var page = new PageImpl<String>(new ArrayList<String>(List.of("test", "test2")));
    var pageable = new PageableList<String>();
    
    pageable.setResult(page);
    
    assertThat(pageable.getTotal()).isEqualTo(page.getTotalElements());
  }

}
