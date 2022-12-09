package app.wooportal.server.core.security.components.verification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
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
@Table(name = "verifications")
public class VerificationEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @Column(
      nullable = false,
      unique = true)
  private String key;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserEntity user;
  
}
