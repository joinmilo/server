package app.wooportal.server.base.cms.pageEmbedding;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.cms.feature.FeatureEntity;
import app.wooportal.server.base.cms.page.PageEntity;
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
@Table(name = "page_embeddings")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class PageEmbeddingEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private FeatureEntity feature;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private PageEntity page;
}
