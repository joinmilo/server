package app.milo.server.core.security.components.user;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.messaging.notifications.base.NotificationEntity;
import app.milo.server.core.push.subscription.SubscriptionEntity;
import app.milo.server.core.security.components.role.application.PrivilegeApplicationEntity;
import app.milo.server.core.security.components.role.base.RoleEntity;
import app.milo.server.core.security.components.user.emailVerification.VerificationEntity;
import app.milo.server.core.security.components.user.passwordReset.PasswordResetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "users")
public class UserEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Transient
  private String captchaToken;
  
  @Column(unique = true, nullable = false)
  private String email;
  
  private String firstName;
  
  private String lastName;
  
  @Column(nullable = false)
  private String password;
   
  private String phone;
  
  @Transient
  private Boolean termsAccepted;
  
  private OffsetDateTime lastLogin;
  
  private Boolean verified;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<NotificationEntity> notifications;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<PasswordResetEntity> passwordResets;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
  private List<RoleEntity> roles = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<PrivilegeApplicationEntity> privilegeApplications;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<SubscriptionEntity> subscriptions;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<VerificationEntity> verifications;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
  
}
