package app.wooportal.server.features.contests.contestParticipations;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.contests.base.ContestEntity;
import app.wooportal.server.features.contests.contestVotes.ContestVoteEntity;
import app.wooportal.server.features.contests.translations.ContestParticipationTranslatableEntity;
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

  @Column(nullable = false)
  private Boolean winner;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ContestParticipationTranslatableEntity> translatable;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contestParticipation")
  private Set<ContestVoteEntity> contestVotes;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity mediaSubmissions;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContestEntity contest;

}
