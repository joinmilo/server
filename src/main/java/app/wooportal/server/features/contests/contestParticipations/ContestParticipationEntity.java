package app.wooportal.server.features.contests.contestParticipations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.contests.base.ContestEntity;
import app.wooportal.server.features.contests.contestParticipations.translations.ContestParticipationTranslatableEntity;
import app.wooportal.server.features.contests.contestVotes.ContestVoteEntity;
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
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ContestEntity contest;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contestParticipation")
  private Set<ContestVoteEntity> contestVotes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ContestParticipationTranslatableEntity> translatable;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "contest_participation_media", joinColumns = @JoinColumn(name = "contest_participation_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"contest_participation_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();
}
