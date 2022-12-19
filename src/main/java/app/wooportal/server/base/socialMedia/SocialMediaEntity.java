package app.wooportal.server.base.socialMedia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "social_media")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class SocialMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String icon;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String url;

}
