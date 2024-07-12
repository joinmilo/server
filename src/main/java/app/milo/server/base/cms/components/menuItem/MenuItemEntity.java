package app.milo.server.base.cms.components.menuItem;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.menuItem.translations.MenuItemTranslatableEntity;
import app.milo.server.base.cms.components.page.base.PageEntity;
import app.milo.server.base.cms.components.plugin.PluginEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
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
  
  private String icon;
  
  @Translatable
  private String name;
  
  private Integer order;
  
  @Translatable
  private String shortDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  private PluginEntity plugin;

  @ManyToOne(fetch = FetchType.LAZY)
  private PageEntity page;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MenuItemEntity parent;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<MenuItemEntity> subMenuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<MenuItemTranslatableEntity> translatables;
}
