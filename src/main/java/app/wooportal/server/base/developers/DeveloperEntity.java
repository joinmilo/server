package app.wooportal.server.base.developers;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.contact.base.ContactEntity;
import app.wooportal.server.base.developers.translations.DeveloperTranslatableEntity;
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
@Table(name = "developer")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class DeveloperEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<DeveloperTranslatableEntity> translatables;

  @OneToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

}
