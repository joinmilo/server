package app.wooportal.server.features.event.schedule;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
import app.wooportal.server.features.event.base.EventEntity;
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
@Table(name = "schedules")
public class ScheduleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(name = "end_date")
  private OffsetDateTime endDate;

  @Column(name = "start_date")
  @DefaultSort
  private OffsetDateTime startDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private EventEntity event;

}
