package app.wooportal.server.features.articles.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.articles.base.visitors.ArticleVisitorEntity;
import app.wooportal.server.features.articles.categories.ArticleCategoryEntity;
import app.wooportal.server.features.articles.comments.ArticleCommentEntity;
import app.wooportal.server.features.articles.publicAuthors.PublicAuthorEntity;
import app.wooportal.server.features.articles.ratings.ArticleRatingEntity;
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
@Table(name = "articles")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ArticleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Boolean approved;

  private String seoDescription;

  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity cardImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private ArticleCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  private PublicAuthorEntity pulbicAuthor;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
  private Set<ArticleCommentEntity> comments;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
  private Set<ArticleRatingEntity> ratings;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ArticleVisitorEntity> visitors;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "article_media", joinColumns = @JoinColumn(name = "article_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"article_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();
}
