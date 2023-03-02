package app.wooportal.server.base.cms.menuItem.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.menuItem.MenuItemEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "menu_item_translatables")
public class MenuItemTranslatableEntity extends TranslatableEntity<MenuItemEntity> {

  private static final long serialVersionUID = 1L;

  private String name;
}
