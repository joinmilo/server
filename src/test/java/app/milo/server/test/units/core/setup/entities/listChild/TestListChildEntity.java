package app.milo.server.test.units.core.setup.entities.listChild;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.config.DefaultSort;
import app.milo.server.test.units.core.setup.entities.base.TestEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "data_child")
@Entity
public class TestListChildEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @Column(unique = true)
  private String name;
  
  @DefaultSort(field = "child")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private TestEntity parent;
  
}
