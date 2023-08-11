package app.wooportal.server.core.security.components.role.roleApplication.translation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.security.components.role.RoleEntity;
import app.wooportal.server.core.security.components.role.roleApplication.RoleApplicationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "role_application_translatables")
public class RoleApplicationTranslatableEntity extends TranslatableEntity<RoleEntity> {

  private static final long serialVersionUID = 1L;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private RoleApplicationEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
