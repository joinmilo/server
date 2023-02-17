package app.wooportal.server.features.events.attendeeConfiguration;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "attendee_configurations")
public class AttendeeConfigurationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean approved;
  
  private Integer maxAttendees;
  
  @OneToMany(mappedBy = "attendeeConfiguration", fetch = FetchType.LAZY)
  private Set<EventEntity> events;
}
