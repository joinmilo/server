package app.wooportal.server.base.cms.page.base;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.cms.components.menuItem.MenuItemEntity;
import app.wooportal.server.base.cms.page.base.media.PageMediaEntity;
import app.wooportal.server.base.cms.page.base.translations.PageTranslatableEntity;
import app.wooportal.server.base.cms.page.base.visitors.PageVisitorEntity;
import app.wooportal.server.base.cms.page.embedding.PageEmbeddingEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.seo.annotations.SlugSource;
import app.wooportal.server.core.seo.annotations.SlugTarget;
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
@Table(name = "pages")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class PageEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String callText;
  
  private String callUrl;
  
  @Translatable
  private String content;
  
  @Column(nullable = false)
  private Boolean isLanding;

  private String metaDescription;
  
  @Translatable
  @SlugSource
  private String name;

  @Column(unique = true, nullable = false)
  @SlugTarget
  private String slug;
  
  @Translatable
  private String shortDescription;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
  private Set<PageEmbeddingEntity> embeddings;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
  private Set<MenuItemEntity> menuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageVisitorEntity> visitors;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
  private Set<PageMediaEntity> uploads;
}
