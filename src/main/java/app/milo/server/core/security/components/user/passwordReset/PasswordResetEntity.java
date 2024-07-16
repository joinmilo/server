package app.milo.server.core.security.components.user.passwordReset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
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
@Table(name = "password_resets")
public class PasswordResetEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @Column(
      nullable = false,
      unique = true)
  private String token;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserEntity user;
  
}
