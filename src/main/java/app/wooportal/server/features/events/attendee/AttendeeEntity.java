package app.wooportal.server.features.events.attendee;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.events.base.EventEntity;
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
@Table(name = "attendees")
public class AttendeeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean approved;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity event;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
}
