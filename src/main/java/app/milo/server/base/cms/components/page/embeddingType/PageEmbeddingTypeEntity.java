package app.milo.server.base.cms.components.page.embeddingType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import app.milo.server.base.cms.components.page.attributeType.PageAttributeTypeEntity;
import app.milo.server.base.cms.components.page.embedding.PageEmbeddingEntity;
import app.milo.server.base.cms.components.page.embeddingType.translations.PageEmbeddingTypeTranslatableEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "page_embedding_types")
public class PageEmbeddingTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String code;
  
  @Translatable
  private String description;
  
  @Translatable
  private String name;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageEmbeddingTypeTranslatableEntity> translatables;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private Set<PageEmbeddingEntity> embeddings;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_embedding_types_attribute_types",
    joinColumns = @JoinColumn(name = "embedding_type_id"),
    inverseJoinColumns = @JoinColumn(name = "attribute_type_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"embedding_type_id", "attribute_type_id"})})
  private List<PageAttributeTypeEntity> attributes = new ArrayList<>();

}
