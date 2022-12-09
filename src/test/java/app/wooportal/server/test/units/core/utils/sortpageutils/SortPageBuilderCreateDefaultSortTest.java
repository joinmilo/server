package app.wooportal.server.test.units.core.utils.sortpageutils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.utils.SortPageUtils;
import app.wooportal.server.test.units.core.utils.sortpageutils.setup.ChildFieldSortEntity;
import app.wooportal.server.test.units.core.utils.sortpageutils.setup.ChildPrimitiveFieldSortEntity;
import app.wooportal.server.test.units.core.utils.sortpageutils.setup.ChildSortEntity;
import app.wooportal.server.test.units.core.utils.sortpageutils.setup.MultipleSortEntity;
import app.wooportal.server.test.units.core.utils.sortpageutils.setup.ParentSortEntity;

public class SortPageBuilderCreateDefaultSortTest {
  
  @Test
  public void createDefaultSort() {
    var entityClass = ParentSortEntity.class;
    var defaultSort = "defaultSort";
    
    var result = SortPageUtils.createDefaultSort(entityClass);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(defaultSort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createDefaultSortForNested() {
    var entityClass = ChildSortEntity.class;
    var defaultSort = "parent.defaultSort";
    
    var result = SortPageUtils.createDefaultSort(entityClass);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(defaultSort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createDefaultSortForNestedWithEntityField() {
    var entityClass = ChildFieldSortEntity.class;
    var defaultSort = "parent.parent.defaultSort";
    
    var result = SortPageUtils.createDefaultSort(entityClass);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(defaultSort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createDefaultSortForNestedWithPrimitiveField() {
    var entityClass = ChildPrimitiveFieldSortEntity.class;
    var defaultSort = "parent.name";
    
    var result = SortPageUtils.createDefaultSort(entityClass);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).anyMatch(order -> order.getProperty().equals(defaultSort)
        && order.getDirection().isAscending());
  }
  
  @Test
  public void createNoDefaultSort() {
    var entityClass = BaseEntity.class;
    
    var result = SortPageUtils.createDefaultSort(entityClass);
    
    assertThat(result.isEmpty()).isTrue();
  }
  
  @Test
  public void createSortWithMultipleSortFields() {
    var entityClass = MultipleSortEntity.class;
    
    var result = catchThrowable(() -> SortPageUtils.createDefaultSort(entityClass));
    
    assertThat(result).isInstanceOfAny(AssertionError.class);
  }
  
  @Test
  public void createDefaultSortWithBadParams() {   
    var result = catchThrowable(() -> SortPageUtils.createDefaultSort(null));
    
    assertThat(result).isInstanceOfAny(IllegalArgumentException.class);
  }

}
