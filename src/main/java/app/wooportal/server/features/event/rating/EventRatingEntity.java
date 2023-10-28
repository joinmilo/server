package app.wooportal.server.features.event.rating;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import app.wooportal.server.base.analytics.rating.RatableEntity;
import app.wooportal.server.features.event.base.EventEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "event_ratings", uniqueConstraints = 
@UniqueConstraint(columnNames = { "parent_id", "user_context_id" }))
public class EventRatingEntity extends RatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;

}
