package app.wooportal.server.features.navigator.page.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.features.navigator.base.NavigatorChoiceEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "navigator_page_translatables")
public class NavigatorPageTranslatableEntity extends TranslatableEntity<NavigatorChoiceEntity> {

  private static final long serialVersionUID = 1L;

  private String additionalInformation;
  
  @Column(nullable = false)
  private String title;

}
