package app.wooportal.server.base.contact.base;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.deals.base.DealEntity;
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
@Table(name = "contacts")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContactEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String email;
  
  private String first_name;
  
  private String last_name;

  @Column(nullable = false)
  private String password;

  private String phone;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
  private Set<DealEntity> deals;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
  private Set<OrganisationEntity> organisations;
}
