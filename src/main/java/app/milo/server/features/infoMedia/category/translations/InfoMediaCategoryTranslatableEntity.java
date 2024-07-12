package app.milo.server.features.infoMedia.category.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.infoMedia.category.InfoMediaCategoryEntity;
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
@Table(name = "info_media_category_translatables")
public class InfoMediaCategoryTranslatableEntity extends TranslatableEntity<InfoMediaCategoryEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;
}
