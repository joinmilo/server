package app.wooportal.server.core.messaging.channels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "channels")
public class ChannelEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(unique = true, nullable = false)
  private String name;
  
  @Column(unique = true, nullable = false)
  private String key;

}
