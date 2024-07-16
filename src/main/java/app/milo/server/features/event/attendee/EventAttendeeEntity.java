package app.milo.server.features.event.attendee;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.features.event.attendeeConfiguration.EventAttendeeConfigurationEntity;
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
@Table(name = "event_attendees")
public class EventAttendeeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @JoinColumn(nullable = false)
  private Boolean approved;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private EventAttendeeConfigurationEntity configuration;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserContextEntity userContext;
}
