package app.wooportal.server.core.security.components.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.messaging.notifications.base.NotificationEntity;
import app.wooportal.server.core.push.subscription.SubscriptionEntity;
import app.wooportal.server.core.security.components.role.RoleEntity;
import app.wooportal.server.core.security.components.user.emailVerification.VerificationEntity;
import app.wooportal.server.core.security.components.user.passwordReset.PasswordResetEntity;
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
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
   
  @Column(unique = true)
  private String phone;
  
  private Boolean verified;
  
  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  private UserContextEntity userContext;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<NotificationEntity> notifications;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<PasswordResetEntity> passwordResets;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<RoleEntity> roles = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<SubscriptionEntity> subscriptions;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<VerificationEntity> verifications;
  
}
