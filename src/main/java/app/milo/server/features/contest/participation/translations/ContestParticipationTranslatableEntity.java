package app.milo.server.features.contest.participation.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.contest.participation.ContestParticipationEntity;
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
@Table(name = "contest_participation_translatables")
public class ContestParticipationTranslatableEntity extends TranslatableEntity<ContestParticipationEntity> {

  private static final long serialVersionUID = 1L;

  private String textSubmission;

}
