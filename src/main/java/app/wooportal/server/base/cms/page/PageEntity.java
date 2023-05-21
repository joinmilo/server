package app.wooportal.server.base.cms.page;

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

import app.wooportal.server.base.cms.menuItem.MenuItemEntity;
import app.wooportal.server.base.cms.page.translations.PageTranslatableEntity;
import app.wooportal.server.base.cms.page.visitors.PageVisitorEntity;
import app.wooportal.server.base.cms.pageFeature.PageFeatureEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.media.base.MediaEntity;
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
  
  private Boolean isLanding;

  private String metaDescription;
  
  @Translatable
  private String name;

  @Column(unique = true, nullable = false)
  private String slug;
  
  @Translatable
  private String shortDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
  private Set<PageFeatureEntity> pageFeatures;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
  private Set<MenuItemEntity> menuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageVisitorEntity> visitors;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_media", joinColumns = @JoinColumn(name = "page_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"page_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();
}
