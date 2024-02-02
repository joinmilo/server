package app.wooportal.server.features.contest.participation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.contest.base.ContestEntity;
import app.wooportal.server.features.contest.participation.media.ContestParticipationMediaEntity;
import app.wooportal.server.features.contest.participation.translations.ContestParticipationTranslatableEntity;
import app.wooportal.server.features.contest.vote.ContestVoteEntity;
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
@Table(name = "contest_participations")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContestParticipationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean approved;
  
  @Translatable
  private String textSubmission;

  private Integer placement;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ContestEntity contest;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserContextEntity userContext;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contestParticipation")
  private Set<ContestVoteEntity> contestVotes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ContestParticipationTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contestParticipation")
  private List<ContestParticipationMediaEntity> mediaSubmissions = new ArrayList<>();
}
