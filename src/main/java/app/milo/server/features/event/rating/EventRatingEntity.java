package app.milo.server.features.event.rating;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.base.analytics.rating.RatableEntity;
import app.milo.server.features.event.base.EventEntity;
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
