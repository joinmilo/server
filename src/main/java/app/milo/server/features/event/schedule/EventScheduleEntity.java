package app.milo.server.features.event.schedule;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.querydsl.core.annotations.QueryInit;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.config.DefaultSort;
import app.milo.server.features.event.base.EventEntity;
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
@Table(name = "event_schedules")
public class EventScheduleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(name = "end_date", nullable = false)
  private OffsetDateTime endDate;

  @Column(name = "start_date", nullable = false)
  @DefaultSort
  private OffsetDateTime startDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @QueryInit(value = {
      "*.*",
  })
  private EventEntity event;

}
