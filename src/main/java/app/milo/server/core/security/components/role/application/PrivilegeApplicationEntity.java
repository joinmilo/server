package app.milo.server.core.security.components.role.application;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.security.components.role.application.translation.PrivilegeApplicationTranslatableEntity;
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
@Table(name = "privilege_applications")
public class PrivilegeApplicationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Boolean accepted;
  
  @Translatable
  private String content;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private RolePrivilegeEntity privilege;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserEntity user;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PrivilegeApplicationTranslatableEntity> translatables;
}
