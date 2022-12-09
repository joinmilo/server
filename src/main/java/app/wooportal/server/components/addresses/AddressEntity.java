package app.wooportal.server.components.addresses;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.components.events.base.EventEntity;
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
@Entity
@Table(name = "address")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class AddressEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
  private Set<EventEntity> events;

  @Column(name = "house_number")
  private String houseNumber;

  @Column(nullable = true)
  private float latitude;

  @Column(nullable = true)
  private float longitude;

  private String place;

  @Column(name = "postal_code")
  private String postalCode;

  private String street;

}
