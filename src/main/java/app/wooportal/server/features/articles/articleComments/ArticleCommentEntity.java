package app.wooportal.server.features.articles.articleComments;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.articles.base.ArticleEntity;
import app.wooportal.server.features.articles.translations.ArticleCommentTranslatableEntity;
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
@Table(name = "article_comments")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ArticleCommentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Boolean approved;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;

  @ManyToOne(fetch = FetchType.LAZY)
  private ArticleEntity article;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ArticleCommentTranslatableEntity> translatables;
}
