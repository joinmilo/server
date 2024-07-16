package app.milo.server.test.units.core.setup.entities.base;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.config.DefaultSort;
import app.milo.server.test.units.core.setup.entities.child.TestChildEntity;
import app.milo.server.test.units.core.setup.entities.listChild.TestListChildEntity;
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
