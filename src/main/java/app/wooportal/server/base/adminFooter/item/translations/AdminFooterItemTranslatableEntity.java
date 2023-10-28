package app.wooportal.server.base.adminFooter.item.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import app.wooportal.server.base.adminFooter.item.AdminFooterItemEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
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
