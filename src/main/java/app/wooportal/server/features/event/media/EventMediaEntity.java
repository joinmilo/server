package app.wooportal.server.features.event.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.event.base.EventEntity;
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
@Table(name = "event_media", uniqueConstraints = 
@UniqueConstraint(columnNames = { "media_id", "event_id" }))
public class EventMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean card;
  
  private Boolean title;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity event;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
}
