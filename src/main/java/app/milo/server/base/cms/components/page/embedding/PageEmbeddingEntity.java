package app.milo.server.base.cms.components.page.embedding;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.page.attribute.PageAttributeEntity;
import app.milo.server.base.cms.components.page.base.PageEntity;
import app.milo.server.base.cms.components.page.embeddingType.PageEmbeddingTypeEntity;
import app.milo.server.core.base.BaseEntity;
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
@Table(name = "page_embeddings")
public class PageEmbeddingEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Integer order;
  
  private String label;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private PageEntity page;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  protected PageEmbeddingTypeEntity type;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "embedding")
  private Set<PageAttributeEntity> attributes;
}
