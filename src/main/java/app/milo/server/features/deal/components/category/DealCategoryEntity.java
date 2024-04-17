package app.milo.server.features.deal.components.category;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.deal.components.base.DealEntity;
import app.milo.server.features.deal.components.category.translations.DealCategoryTranslatableEntity;
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

  @JoinColumn(nullable = false)
  private String color;

  @JoinColumn(nullable = false)
  private String icon;
  
  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
  private Set<DealEntity> deals;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<DealCategoryTranslatableEntity> translatables;

}
