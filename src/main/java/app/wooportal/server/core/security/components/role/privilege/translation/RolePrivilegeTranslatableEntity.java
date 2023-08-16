package app.wooportal.server.core.security.components.role.privilege.translation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.security.components.role.privilege.RolePrivilegeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "role_privilege_translatables")
public class RolePrivilegeTranslatableEntity extends TranslatableEntity<RolePrivilegeEntity> {

  private static final long serialVersionUID = 1L;

  private String description;
  
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private RolePrivilegeEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
