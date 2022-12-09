package app.wooportal.server.components.blogs;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.components.bloggers.BloggerEntity;
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
@Table(name = "bloggers")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class BlogEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String author;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Set<BloggerEntity> blogger;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String title;
  
  @Column(nullable = false, unique = true)
  private String topicId;
}
