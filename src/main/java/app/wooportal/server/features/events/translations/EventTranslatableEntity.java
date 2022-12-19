package app.wooportal.server.features.events.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.i18n.language.entities.TranslatableEntity;
import app.wooportal.server.features.events.base.EventEntity;
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
public class EventTranslatableEntity extends TranslatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;

  private String description;

  @Column(nullable = false)
  private String name;

  private String shortDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private EventEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private LanguageEntity language;
}
