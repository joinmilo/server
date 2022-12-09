package app.wooportal.server.components.events.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.components.events.base.EventEntity;
import app.wooportal.server.core.visit.visitable.VisitableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "event_visitors")
public class EventVisitorEntity extends VisitableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;
}
