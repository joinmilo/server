package app.wooportal.server.features.navigator.choice;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.seo.annotations.SlugSource;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.features.navigator.choice.translations.NavigatorChoiceTranslatableEntity;
import app.wooportal.server.features.navigator.page.NavigatorPageEntity;
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
@Table(name = "navigator_choices")
public class NavigatorChoiceEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String description;
  
  @Translatable
  @SlugSource
  private String name;
  
  @Column(nullable = false, unique = true)
  @SlugTarget
  private String slug;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private NavigatorPageEntity parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private NavigatorPageEntity nextPage;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<NavigatorChoiceTranslatableEntity> translatables;
}
