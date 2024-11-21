package app.milo.server.base.adminFooter.item.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.base.adminFooter.item.AdminFooterItemEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "admin_footer_item_translatables")
public class AdminFooterItemTranslatableEntity extends TranslatableEntity<AdminFooterItemEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

}
