package app.milo.server.base.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
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
@Table(name = "configurations")
public class ConfigurationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(
      nullable = false,
      unique = true)
  private String code;
  
  private String value;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;

}
