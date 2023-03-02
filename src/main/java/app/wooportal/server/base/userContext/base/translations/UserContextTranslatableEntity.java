package app.wooportal.server.base.userContext.base.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
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
@Table(name = "user_context_translatables")
public class UserContextTranslatableEntity extends TranslatableEntity<UserContextEntity> {

  private static final long serialVersionUID = 1L;

  private String description;

}
