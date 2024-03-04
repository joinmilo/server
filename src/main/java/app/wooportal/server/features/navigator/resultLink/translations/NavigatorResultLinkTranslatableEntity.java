package app.wooportal.server.features.navigator.resultLink.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.features.navigator.resultLink.NavigatorResultLinkEntity;
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
@Table(name = "navigator_result_link_translatables")
public class NavigatorResultLinkTranslatableEntity extends TranslatableEntity<NavigatorResultLinkEntity> {

  private static final long serialVersionUID = 1L;
  
  private String name;
}
