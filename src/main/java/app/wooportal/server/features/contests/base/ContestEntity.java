package app.wooportal.server.features.contests.base;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.contests.contestParticipations.ContestParticipationEntity;
import app.wooportal.server.features.contests.contestState.ContestStateEntity;
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
@Table(name = "contests")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContestEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String seoDescription;

  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;

  @Column(nullable = false)
  private Boolean votable;

  @Column(nullable = false)
  private Boolean multiVote;

  @Column(nullable = false)
  private Boolean multiSubmission;

  @Column(nullable = false)
  private Boolean offer;

  private Date dueDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContestStateEntity state;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
  private Set<ContestParticipationEntity> contestParticipation;

}
