package app.wooportal.server.test.units.core.utils.sortpageutils.setup;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MultipleSortEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @DefaultSort
  private String defaultSort1;
  
  @DefaultSort
  private String defaultSort2;

}
