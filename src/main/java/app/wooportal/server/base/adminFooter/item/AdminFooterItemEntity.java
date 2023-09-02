package app.wooportal.server.base.adminFooter.item;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.adminFooter.item.translations.AdminFooterItemTranslatableEntity;
import app.wooportal.server.base.adminFooter.parent.AdminFooterParentEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
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
@Table(name = "admin_footer_items")
public class AdminFooterItemEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String name;
  
  @Column(nullable = false)
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private AdminFooterParentEntity parent;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<AdminFooterItemTranslatableEntity> translatables;

}
