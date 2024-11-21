package app.milo.server.features.contest.participation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.contest.base.ContestEntity;
import app.milo.server.features.contest.participation.media.ContestParticipationMediaEntity;
import app.milo.server.features.contest.participation.translations.ContestParticipationTranslatableEntity;
import app.milo.server.features.contest.vote.ContestVoteEntity;
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
  
  @Formula("""
      (SELECT COUNT(cv.id)
      FROM contest_votes cv
      WHERE cv.contest_participation_id = id)
    """)
  private Integer voteAmount;
}
