package app.wooportal.server.features.event.targetGroup.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.features.event.targetGroup.EventTargetGroupEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "event_target_group_translatables")
public class EventTargetGroupTranslatableEntity extends TranslatableEntity<EventTargetGroupEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;
}
