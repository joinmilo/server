package app.wooportal.server.features.events.eventComments;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.events.translations.EventCommentTranslatableEntity;
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
@Table(name = "event_comments")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class EventCommentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity event;

  private Boolean approved;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<EventCommentTranslatableEntity> translateable;
}
