package app.milo.server.base.cms.components.page.base;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.base.cms.components.menuItem.MenuItemEntity;
import app.milo.server.base.cms.components.page.base.visitors.PageVisitorEntity;
import app.milo.server.base.cms.components.page.embedding.PageEmbeddingEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.seo.annotations.SlugSource;
import app.milo.server.core.seo.annotations.SlugTarget;
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
public class PageEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @SlugSource
  private String label;
  
  private Boolean isLanding;

  private String metaDescription;

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
  private Set<PageVisitorEntity> visitors;

}
