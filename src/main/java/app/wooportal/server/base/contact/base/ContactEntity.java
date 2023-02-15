package app.wooportal.server.base.contact.base;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.developers.DeveloperEntity;
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
@Table(name = "contacts")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContactEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String email;

  private String name;

  
  private String phone;

  private Boolean preferredContact;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
  private Set<EventEntity> events;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
  private Set<DealEntity> deals;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
  private Set<OrganisationEntity> organisations;

  @OneToOne(mappedBy = "contact", fetch = FetchType.LAZY)
  private DeveloperEntity developer;
}
