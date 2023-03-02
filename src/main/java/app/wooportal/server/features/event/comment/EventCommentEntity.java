package app.wooportal.server.features.event.comment;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.comment.translations.EventCommentTranslatableEntity;
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
public class EventCommentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Boolean approved;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity event;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<EventCommentTranslatableEntity> translatables;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
}
