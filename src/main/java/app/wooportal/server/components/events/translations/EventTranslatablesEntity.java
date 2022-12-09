package app.wooportal.server.components.events.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import app.wooportal.server.components.events.base.EventEntity;
import app.wooportal.server.core.i18n.language.entities.TranslatableEntity;
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
@Table(name = "event_translatables")
public class EventTranslatablesEntity extends TranslatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String description;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String shortDescription;
  
  @Column(nullable = false)
  private String name;
}
