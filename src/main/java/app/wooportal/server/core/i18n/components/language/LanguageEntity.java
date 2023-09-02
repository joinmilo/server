package app.wooportal.server.core.i18n.components.language;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
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
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean active;

  @Column(nullable = false, unique = true)
  private String locale;

  @Column(nullable = false, unique = true)
  private String name;
  
  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<UserEntity> users;
}
