package app.wooportal.server.test.units.core.setup.entities.child;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
import app.wooportal.server.test.units.core.setup.entities.base.TestEntity;
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
public class TestChildEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @Column(unique = true)
  private String name;
  
  @DefaultSort
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private TestEntity parent;
}
