package app.wooportal.server.base.cms.components.page.embeddingType.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.components.page.embeddingType.PageEmbeddingTypeEntity;
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
@Table(name = "page_embedding_type_translatables")
public class PageEmbeddingTypeTranslatableEntity
    extends TranslatableEntity<PageEmbeddingTypeEntity> {

  private static final long serialVersionUID = 1L;
  
  private String description;

  @Column(nullable = false)
  private String name;

}
