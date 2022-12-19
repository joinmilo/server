package app.wooportal.server.features.articles.articleCategories;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.articles.base.ArticleEntity;
import app.wooportal.server.features.articles.translations.ArticleCategoryTranslatableEntity;
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
@Table(name = "article_categories")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ArticleCategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String color;

  @Column(nullable = false)
  private String icon;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
  private Set<ArticleEntity> articles;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ArticleCategoryTranslatableEntity> articleCategories;
}
