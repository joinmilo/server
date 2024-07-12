package app.milo.server.core.security.components.role.privilege.translation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.security.components.role.privilege.RolePrivilegeEntity;
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

  @Column(nullable = false)
  private String description;
  
  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private RolePrivilegeEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private LanguageEntity language;
}
