package app.wooportal.server.features.article.base;

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.seo.annotations.SlugSource;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.features.article.base.media.ArticleMediaEntity;
import app.wooportal.server.features.article.base.translations.ArticleTranslatableEntity;
import app.wooportal.server.features.article.base.visitors.ArticleVisitorEntity;
import app.wooportal.server.features.article.category.ArticleCategoryEntity;
import app.wooportal.server.features.article.comment.ArticleCommentEntity;
import app.wooportal.server.features.article.publicAuthor.ArticlePublicAuthorEntity;
import app.wooportal.server.features.article.rating.ArticleRatingEntity;
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

  @Transient
  private String captchaToken;

  @Translatable
  private String content;

  @Translatable
  private String shortDescription;

  private String metaDescription;

  @Translatable
  @SlugSource
  private String name;

  @Column(nullable = false, unique = true)
  @SlugTarget
  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;

  @Translatable
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  private ArticleCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  private ArticlePublicAuthorEntity publicAuthor;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity author;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
  private Set<ArticleCommentEntity> comments;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
  private Set<ArticleRatingEntity> ratings;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<ArticleTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ArticleVisitorEntity> visitors;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
  private Set<ArticleMediaEntity> uploads;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_articles", joinColumns = @JoinColumn(name = "article_id"),
      inverseJoinColumns = @JoinColumn(name = "user_context_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "article_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<UserContextEntity> favoritingUsers = new ArrayList<>();
  
}
