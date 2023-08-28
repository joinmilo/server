package app.wooportal.server.core.security.components.role.privilege;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.security.components.role.application.PrivilegeApplicationEntity;
import app.wooportal.server.core.security.components.role.privilege.translation.RolePrivilegeTranslatableEntity;
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
@Table(name = "role_privileges")
public class RolePrivilegeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false, unique = true)
  private String code;
  
  @Translatable
  private String description;
  
  @Translatable
  private String name;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "privilege")
  private Set<PrivilegeApplicationEntity> applications;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<RolePrivilegeTranslatableEntity> translatables;
  
}
