package app.milo.server.base.cms.components.page.attribute.translations;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.page.attribute.PageAttributeEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
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
@Table(name = "page_attribute_translatables")
public class PageAttributeTranslatableEntity extends TranslatableEntity<PageAttributeEntity> {

  private static final long serialVersionUID = 1L;

  private String translatable;

}
