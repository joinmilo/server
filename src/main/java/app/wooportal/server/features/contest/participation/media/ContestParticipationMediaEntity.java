package app.wooportal.server.features.contest.participation.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.contest.participation.ContestParticipationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "contest_participation_media")
public class ContestParticipationMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ContestParticipationEntity contestParticipation;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
}
