package app.wooportal.server.test.units.core.utils.persistence.setup;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "persistence_test")
public class PersistenceValidityEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private String validField;
  
  @Transient
  private String transientField;
  
  @Column(nullable = true)
  private String nullableField;
  
  @Column(nullable = false)
  private String notNullableField;
  
  @JoinColumn(nullable = true)
  private String nullableJoinField;
  
  @JoinColumn(nullable = false)
  private String notNullableJoinField;

}
