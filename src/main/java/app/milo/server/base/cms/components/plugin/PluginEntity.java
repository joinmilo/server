package app.milo.server.base.cms.components.plugin;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.menuItem.MenuItemEntity;
import app.milo.server.base.cms.components.plugin.translations.PluginTranslatableEntity;
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
@Table(name = "plugins")
public class PluginEntity extends BaseEntity {
  	
  private Boolean active;

  private static final long serialVersionUID = 1L;
  
  private String code;
  
  private String icon;
  
  @Translatable
  private String name;

  @Translatable
  private String shortDescription;

  private Boolean released;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
  private Set<MenuItemEntity> menuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PluginTranslatableEntity> translatables;
}
