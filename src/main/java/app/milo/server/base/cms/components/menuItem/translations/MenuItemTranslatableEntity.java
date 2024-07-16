package app.milo.server.base.cms.components.menuItem.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.menuItem.MenuItemEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
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
  
  private String shortDescription;

  @Column(nullable = false)
  private String name;
}
