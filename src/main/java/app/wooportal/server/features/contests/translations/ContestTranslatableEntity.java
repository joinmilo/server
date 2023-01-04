package app.wooportal.server.features.contests.translations;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.features.contests.base.ContestEntity;
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
@Table(name = "contest_translatables")
public class ContestTranslatableEntity extends TranslatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;

  private String content;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContestEntity parent;
}
