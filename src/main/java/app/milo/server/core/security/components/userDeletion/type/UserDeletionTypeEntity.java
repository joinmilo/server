package app.milo.server.core.security.components.userDeletion.type;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.security.components.userDeletion.type.translations.UserDeletionTypeTranslatableEntity;
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
@Table(name = "user_deletion_types")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class UserDeletionTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String name;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<UserDeletionTypeTranslatableEntity> translatables;
 
}
