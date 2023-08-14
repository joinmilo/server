package app.wooportal.server.core.security.components.role.rolePrivilege;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.security.components.role.roleApplication.RoleApplicationEntity;
import app.wooportal.server.core.security.components.role.rolePrivilege.translation.RolePrivilegeTranslatableEntity;
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
  
  @Translatable
  private String description;
  
  @Translatable
  private String name;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "privilege")
  private Set<RoleApplicationEntity> roleApplications;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<RolePrivilegeTranslatableEntity> translatables;
  
}