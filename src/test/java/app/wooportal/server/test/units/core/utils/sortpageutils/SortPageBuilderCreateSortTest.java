package app.wooportal.server.test.units.core.utils.sortpageutils;

import static app.wooportal.server.test.units.core.setup.services.ObjectFactory.newInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.utils.SortPageUtils;

public class SortPageBuilderCreateSortTest {
  
  @Test
  public void createSortWithSortDesc() {
    var sort = "test";
    var dir = "DESC";
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "sort", sort,
        "dir", dir));
    
    var result = SortPageUtils.createSort(params);
    
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(sort)
        && order.getDirection().isDescending());
  }
  
  @Test
  public void createSortWithSortAsc() {
    var sort = "test";
    var dir = "ASC";
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "sort", sort,
        "dir", dir));
    
    var result = SortPageUtils.createSort(params);
    
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(sort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createSortWithDefaultDirection() {
    var sort = "test";
    var params = newInstance(FilterSortPaginate.class, Map.of(
        "sort", sort));
    
    var result = SortPageUtils.createSort(params);
    
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(sort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createSortWithBadParams() {
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> SortPageUtils.createSort(null)));
    result.add(catchThrowable(() -> SortPageUtils.createSort(new FilterSortPaginate())));
    result.add(catchThrowable(() -> SortPageUtils.createSort(newInstance(FilterSortPaginate.class, Map.of(
            "sort", "")))));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }
}
