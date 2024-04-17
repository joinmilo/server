package app.milo.server.core.security.components.userDeletion.base.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.security.components.userDeletion.base.UserDeletionEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "user_deletion_translatables")
public class UserDeletionTranslatableEntity extends TranslatableEntity<UserDeletionEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

}
