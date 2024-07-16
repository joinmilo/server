package app.milo.server.features.event.media;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
import app.milo.server.features.event.base.EventEntity;
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
  
  private Boolean card = false;
  
  private Boolean title = false;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity event;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
}
