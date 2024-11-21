package app.milo.server.features.event.targetGroup.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.event.targetGroup.EventTargetGroupEntity;
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
