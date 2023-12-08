package app.wooportal.server.base.cms.page.embeddingType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.cms.page.attributeType.PageAttributeTypeEntity;
import app.wooportal.server.base.cms.page.embedding.PageEmbeddingEntity;
import app.wooportal.server.base.cms.page.embeddingType.translations.PageEmbeddingTypeTranslatableEntity;
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
@Table(name = "page_embedding_types")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<PageAttributeTypeEntity> attributes = new ArrayList<>();

}
