package app.wooportal.server.components.events.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
@Table(name = "contacts")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContactEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToOne(mappedBy = "contact", fetch = FetchType.LAZY)
  private EventEntity event;

  @Column(unique = true)
  private String mail;

  @Column(unique = true)
  private String name;

  @Column(unique = true)
  private String phone;

  @Column(unique = true)
  private String website;
}
