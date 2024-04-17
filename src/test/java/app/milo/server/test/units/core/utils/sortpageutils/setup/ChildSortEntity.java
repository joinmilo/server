package app.milo.server.test.units.core.utils.sortpageutils.setup;

import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.config.DefaultSort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ChildSortEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @DefaultSort
  private ParentSortEntity parent;
  
  private String name;
}
