package app.wooportal.server.base.addresses;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.deals.base.DealEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
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

  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
  private Set<OrganisationEntity> organisations;

  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
  private Set<DealEntity> deals;

  @Column(nullable = false)
  private String houseNumber;

  @Column(nullable = false)
  private float latitude;

  @Column(nullable = false)
  private float longitude;

  @Column(nullable = false)
  private String place;

  @Column(nullable = false)
  private String postalCode;

  @Column(nullable = false)
  private String street;
}
