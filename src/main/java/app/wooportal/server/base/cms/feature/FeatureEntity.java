package app.wooportal.server.base.cms.feature;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.feature.translations.FeatureTranslatableEntity;
import app.wooportal.server.base.cms.menuItem.MenuItemEntity;
import app.wooportal.server.base.cms.pageFeature.PageFeatureEntity;
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
@Table(name = "features")
public class FeatureEntity extends BaseEntity {
  
  private Boolean active;

  private static final long serialVersionUID = 1L;
  
  private String code;
  
  private String icon;
  
  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature")
  private Set<PageFeatureEntity> pageFeatures;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature")
  private Set<MenuItemEntity> menuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<FeatureTranslatableEntity> translatables;
}
