package app.wooportal.server.base.cms.page.attributeType.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.page.attribute.PageAttributeEntity;
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
@Table(name = "page_attribute_types_translatables")
public class PageAttributeTypeTranslatableEntity
    extends TranslatableEntity<PageAttributeEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

}
