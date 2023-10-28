package app.wooportal.server.test.units.core.utils.sortpageutils;

import static app.wooportal.server.test.units.core.setup.services.ObjectFactory.newInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.utils.SortPageUtils;

public class SortPageBuilderCreatePageTest {
  
  @Test
  public void createPageWithAllParams() {
    var page = 5;
    var size = 10;
    var sort = "test";
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "page", page,
        "size", size,
        "sort", sort));
    
    var result = SortPageUtils.createPage(params);
    
    assertThat(result.getPageNumber()).isEqualTo(page);
    assertThat(result.getPageSize()).isEqualTo(size);
    assertThat(result.getSort()).anyMatch(order -> order.getProperty().equals(sort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createPageWithDefaultPage() {
    var size = 10;
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "size", size));
    
    var result = SortPageUtils.createPage(params);
    
    assertThat(result.getPageNumber()).isEqualTo(SortPageUtils.PAGE_NUMBER_DEFAULT);
    assertThat(result.getPageSize()).isEqualTo(size);
    assertThat(result.getSort()).isEmpty();
  }
  
  @Test
  public void createPageWithDefaultSize() {
    var page = 10;
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "page", page));
    
    var result = SortPageUtils.createPage(params);
    
    assertThat(result.getPageNumber()).isEqualTo(page);
    assertThat(result.getPageSize()).isEqualTo(SortPageUtils.PAGE_SIZE_DEFAULT);
    assertThat(result.getSort()).isEmpty();
  }
  
  @Test
  public void createPageWithDefaultsAndEmptySort() {
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "sort", ""));
    
    var result = SortPageUtils.createPage(params);
    
    assertThat(result.getPageNumber()).isEqualTo(SortPageUtils.PAGE_NUMBER_DEFAULT);
    assertThat(result.getPageSize()).isEqualTo(SortPageUtils.PAGE_SIZE_DEFAULT);
    assertThat(result.getSort()).isEmpty();
  }
  
  @Test
  public void createPageWithBadParams() {
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> SortPageUtils.createPage(null)));
    result.add(catchThrowable(() -> SortPageUtils.createPage(newInstance(FilterSortPaginate.class, Map.of(
            "page", -1)))));
    result.add(catchThrowable(() -> SortPageUtils.createPage(newInstance(FilterSortPaginate.class, Map.of(
            "size", -1)))));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
