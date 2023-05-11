package app.wooportal.server.features.deal.category;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.deal.base.DealEntity;
import app.wooportal.server.features.deal.category.translations.DealCategoryTranslatableEntity;
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
@Table(name = "deal_categories")
public class DealCategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String color;

  private String icon;
  
  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
  private Set<DealEntity> deals;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<DealCategoryTranslatableEntity> translatables;

}
