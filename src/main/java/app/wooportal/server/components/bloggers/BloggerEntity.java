package app.wooportal.server.components.bloggers;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.components.blogs.BlogEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
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
@Table(name = "bloggers")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class BloggerEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity avatar;

  @OneToMany(mappedBy = "blogger", fetch = FetchType.LAZY)
  private Set<BlogEntity> blogs;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToOne(mappedBy = "blogger", fetch = FetchType.LAZY)
  private UserEntity user;
}
