package app.wooportal.server.core.security.components.userDeletion.type.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.security.components.userDeletion.type.UserDeletionTypeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "user_deletion_type_translatables")
public class UserDeletionTypeTranslatableEntity extends TranslatableEntity<UserDeletionTypeEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

}
