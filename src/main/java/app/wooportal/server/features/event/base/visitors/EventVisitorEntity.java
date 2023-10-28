package app.wooportal.server.features.event.base.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;

import app.wooportal.server.core.visit.visitable.VisitableEntity;
import app.wooportal.server.features.event.base.EventEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "event_visitors")
public class EventVisitorEntity extends VisitableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;
  
}
