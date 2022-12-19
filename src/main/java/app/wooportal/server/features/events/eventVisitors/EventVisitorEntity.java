package app.wooportal.server.features.events.eventVisitors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.visit.visitor.VisitorEntity;
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
@Table(name = "event_visitors")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class EventVisitorEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Integer visits;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private VisitorEntity visitor;
}
