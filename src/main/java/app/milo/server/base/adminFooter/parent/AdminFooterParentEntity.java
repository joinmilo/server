package app.milo.server.base.adminFooter.parent;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.milo.server.base.adminFooter.item.AdminFooterItemEntity;
import app.milo.server.base.adminFooter.parent.translations.AdminFooterParentTranslatableEntity;
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
@Table(name = "admin_footer_parents")
public class AdminFooterParentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String name;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<AdminFooterItemEntity> items;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<AdminFooterParentTranslatableEntity> translatables;

}
