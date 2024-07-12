package app.milo.server.features.navigator.resultLink;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.navigator.page.NavigatorPageEntity;
import app.milo.server.features.navigator.resultLink.translations.NavigatorResultLinkTranslatableEntity;
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
@Table(name = "navigator_result_links")
public class NavigatorResultLinkEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String name;
  
  private String url;
  
  @ManyToOne(fetch = FetchType.LAZY)
  protected NavigatorPageEntity page;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<NavigatorResultLinkTranslatableEntity> translatables;
  
}
