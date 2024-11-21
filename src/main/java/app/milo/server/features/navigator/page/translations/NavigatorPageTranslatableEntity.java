package app.milo.server.features.navigator.page.translations;

import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.navigator.page.NavigatorPageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class NavigatorPageTranslatableEntity extends TranslatableEntity<NavigatorPageEntity> {

  private static final long serialVersionUID = 1L;

  private String additionalInformation;
  
  @Column(nullable = false)
  private String title;
}
