package app.wooportal.server.base.cms.menuItems;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.features.FeatureEntity;
import app.wooportal.server.base.cms.menuItems.translations.MenuItemTranslatableEntity;
import app.wooportal.server.base.cms.pages.PageEntity;
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
@Table(name = "menu_items")
public class MenuItemEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean header;
  
  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  private FeatureEntity module;

  @ManyToOne(fetch = FetchType.LAZY)
  private PageEntity page;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MenuItemEntity parent;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<MenuItemTranslatableEntity> translatables;
}
