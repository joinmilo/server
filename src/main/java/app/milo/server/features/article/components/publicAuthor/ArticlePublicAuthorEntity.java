package app.milo.server.features.article.components.publicAuthor;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.features.article.components.base.ArticleEntity;
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
@Table(name = "article_public_authors")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ArticlePublicAuthorEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String name;

  private String phone;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "publicAuthor")
  private Set<ArticleEntity> articles;
}
