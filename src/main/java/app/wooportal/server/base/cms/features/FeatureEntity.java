package app.wooportal.server.base.cms.features;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.features.translations.FeatureTranslatableEntity;
import app.wooportal.server.base.cms.menuItems.MenuItemEntity;
import app.wooportal.server.base.cms.pageFeatures.PageFeatureEntity;
import app.wooportal.server.core.base.BaseEntity;
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

  private static final long serialVersionUID = 1L;

  private String key;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature")
  private Set<PageFeatureEntity> pageFeatures;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature")
  private Set<MenuItemEntity> menuItem;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<FeatureTranslatableEntity> translatables;
}
