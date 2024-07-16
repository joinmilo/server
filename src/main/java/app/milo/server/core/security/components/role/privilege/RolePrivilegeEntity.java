package app.milo.server.core.security.components.role.privilege;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.security.components.role.application.PrivilegeApplicationEntity;
import app.milo.server.core.security.components.role.base.RoleEntity;
import app.milo.server.core.security.components.role.privilege.translation.RolePrivilegeTranslatableEntity;
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
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "role_role_privileges", joinColumns = @JoinColumn(name = "role_privilege_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "role_privilege_id"})})
  
  private List<RoleEntity> roles = new ArrayList<>();
  
}
