package app.milo.server.base.address.suburb;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.address.base.AddressEntity;
import app.milo.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "suburbs")
public class SuburbEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false, unique = true)
  private String name;

  private Float latitude;

  private Float longitude;

  @OneToMany(mappedBy = "suburb", fetch = FetchType.LAZY)
  private Set<AddressEntity> addresses;
  
}
