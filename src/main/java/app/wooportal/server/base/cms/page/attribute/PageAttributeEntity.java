package app.wooportal.server.base.cms.page.attribute;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.page.attribute.translations.PageAttributeTranslatableEntity;
import app.wooportal.server.base.cms.page.attributeReference.PageAttributeReferenceEntity;
import app.wooportal.server.base.cms.page.attributeType.PageAttributeTypeEntity;
import app.wooportal.server.base.cms.page.embedding.PageEmbeddingEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
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
@Table(name = "page_attributes")
public class PageAttributeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String translatable;
  
  private String value;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private PageEmbeddingEntity embedding;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  protected PageAttributeTypeEntity type;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
  private Set<PageAttributeReferenceEntity> references;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageAttributeTranslatableEntity> translatables;

}
