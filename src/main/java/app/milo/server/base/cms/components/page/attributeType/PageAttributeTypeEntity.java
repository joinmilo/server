package app.milo.server.base.cms.components.page.attributeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import app.milo.server.base.cms.components.page.attribute.PageAttributeEntity;
import app.milo.server.base.cms.components.page.embeddingType.PageEmbeddingTypeEntity;
import app.milo.server.core.base.BaseEntity;
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
@Table(name = "page_attribute_types")
public class PageAttributeTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String code;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private Set<PageAttributeEntity> attributes;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_embedding_types_attribute_types",
    joinColumns = @JoinColumn(name = "attribute_type_id"),
    inverseJoinColumns = @JoinColumn(name = "embedding_type_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"attribute_type_id", "embedding_type_id"})})
  private List<PageEmbeddingTypeEntity> embeddingTypes = new ArrayList<>();

}
