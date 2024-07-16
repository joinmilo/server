package app.milo.server.features.deal.components.base.media;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
import app.milo.server.features.deal.components.base.DealEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "deal_media", uniqueConstraints = 
@UniqueConstraint(columnNames = { "media_id", "deal_id" }))
public class DealMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean card;
  
  private Boolean title;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private DealEntity deal;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private MediaEntity media;
  
}
