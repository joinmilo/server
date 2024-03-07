package app.wooportal.server.features.navigator.page;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.seo.annotations.SlugSource;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.features.navigator.choice.NavigatorChoiceEntity;
import app.wooportal.server.features.navigator.page.translations.NavigatorPageTranslatableEntity;
import app.wooportal.server.features.navigator.resultLink.NavigatorResultLinkEntity;
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
@Table(name = "navigator_pages")
public class NavigatorPageEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String additionalInformation;
  
  private Boolean isResultPage;
  
  @Column(nullable = false, unique = true)
  @SlugTarget
  private String slug;
  
  @Translatable
  @SlugSource
  private String title;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<NavigatorChoiceEntity> choices;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
  protected Set<NavigatorResultLinkEntity> links;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "nextPage")
  protected Set<NavigatorChoiceEntity> parentChoices;  
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<NavigatorPageTranslatableEntity> translatables;
}
