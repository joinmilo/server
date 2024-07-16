package app.milo.server.core.security.components.role.base;

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
import app.milo.server.core.security.components.role.base.translation.RoleTranslatableEntity;
import app.milo.server.core.security.components.role.privilege.RolePrivilegeEntity;
import app.milo.server.core.security.components.user.UserEntity;
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
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<RoleTranslatableEntity> translatables;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
  
  private List<UserEntity> users = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "role_role_privileges", joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "role_privilege_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "role_privilege_id"})})
  
  private List<RolePrivilegeEntity> privileges = new ArrayList<>();
}
