package app.milo.server.features.event.base.visitors;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.visit.visitable.VisitableEntity;
import app.milo.server.features.event.base.EventEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "event_visitors")
public class EventVisitorEntity extends VisitableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;
  
}
