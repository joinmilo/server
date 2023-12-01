package app.wooportal.server.base.cms.page.widgetType.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.page.widgetType.PageWidgetTypeEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "page_widget_type_translatables")
public class PageWidgetTypeTranslatableEntity
    extends TranslatableEntity<PageWidgetTypeEntity> {

  private static final long serialVersionUID = 1L;
  
  private String description;

  @Column(nullable = false)
  private String name;

}
