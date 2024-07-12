package app.milo.server.base.adminFooter.parent.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.base.adminFooter.parent.AdminFooterParentEntity;
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
@Table(name = "admin_footer_parent_translatables")
public class AdminFooterParentTranslatableEntity extends TranslatableEntity<AdminFooterParentEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

}
