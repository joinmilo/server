package app.wooportal.server.test.units.core.utils.persistence.setup;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.SetNullOnRemoval;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "test", uniqueConstraints =
  @UniqueConstraint(columnNames = { "entity_id", "field" }))
public class PersistenceEqualityEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @JoinColumn
  private PersistenceValidityEntity unNamedEntity;
  
  @JoinColumn(name = "entity")
  private PersistenceValidityEntity namedEntity;
  
  @JoinColumn
  @OneToOne(mappedBy = "test")
  private PersistenceValidityEntity mappedOneToOne;
  
  @JoinColumn
  @OneToMany(mappedBy = "test")
  private PersistenceValidityEntity mappedOneToMany;
  
  @JoinColumn
  @OneToOne
  private PersistenceValidityEntity unmappedOneToOne;
  
  private String field;
  
  private String notUniqueClass;
  
  @SetNullOnRemoval
  private String setNull;
  
  @Column(unique = true)
  private String uniqueColumn;
  
  @Column(unique = false)
  private String notUniqueColumn;

  @Table(name = "test", uniqueConstraints =
    @UniqueConstraint(columnNames = { }))
  public class NullUniqueConstraints { }

}
