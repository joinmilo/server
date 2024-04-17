package app.milo.server.base.thirdparty.appPlatform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.base.BaseEntity;
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
@Table(name = "app_platforms")
public class AppPlatformEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false, unique = true)
  private String name;
  
  @Column(nullable = false, unique = true)
  private String code;

}
