package app.milo.server.test.units.core.utils.reflection.setup;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.config.DefaultSort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "reflection_test")
public class ReflectionTestEntity extends BaseEntity {
  private static final long serialVersionUID = 1L;
  
  @DefaultSort
  private String field;
  
  @Transient
  private String transientField;
  
  @OneToMany(mappedBy = "test")
  private String mappedByField;
  
  @OneToMany
  private String unMappedByField;
  
}
