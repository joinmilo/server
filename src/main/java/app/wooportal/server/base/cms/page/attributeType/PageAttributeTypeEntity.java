package app.wooportal.server.base.cms.page.attributeType;

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
import app.wooportal.server.base.cms.page.attribute.PageAttributeEntity;
import app.wooportal.server.base.cms.page.embeddingType.PageEmbeddingTypeEntity;
import app.wooportal.server.core.base.BaseEntity;
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
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<PageEmbeddingTypeEntity> embeddingTypes = new ArrayList<>();

}
