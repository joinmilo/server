package app.milo.server.features.navigator.choice;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.seo.annotations.SlugSource;
import app.milo.server.core.seo.annotations.SlugTarget;
import app.milo.server.features.navigator.choice.translations.NavigatorChoiceTranslatableEntity;
import app.milo.server.features.navigator.page.NavigatorPageEntity;
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
