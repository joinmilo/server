package app.wooportal.server.test.units.core.setup.entities.base;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildEntity;
import app.wooportal.server.test.units.core.setup.entities.listChild.TestListChildEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "data_test", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "child_id", "name" })
})
@Entity
public class TestEntity extends BaseEntity {
    
  private static final long serialVersionUID = 1L;
  
  @DefaultSort
  private String name;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = true)
  private TestChildEntity child;
  
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private List<TestListChildEntity> childs;
  
  @OneToOne(
      fetch = FetchType.LAZY,
      mappedBy = "parent")
  @JoinColumn(nullable = true)
  private TestChildEntity oneToOneChild;
 
  private String removeContextField;
  
  private String setContext;
  
}
