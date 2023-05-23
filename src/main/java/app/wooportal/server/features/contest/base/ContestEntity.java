package app.wooportal.server.features.contest.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.contest.base.media.ContestMediaEntity;
import app.wooportal.server.features.contest.base.translations.ContestTranslatableEntity;
import app.wooportal.server.features.contest.participation.ContestParticipationEntity;
import app.wooportal.server.features.contest.state.ContestStateEntity;
import app.wooportal.server.features.contest.type.ContestTypeEntity;
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
public class ContestEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String content;

  private Date dueDate;

  @Translatable
  private String name;

  @Translatable
  private String shortDescription;

  private String metaDescription;

  @Column(nullable = false, unique = true)
  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;

  @Column(nullable = false)
  private Boolean voteable;

  @Column(nullable = false)
  private Boolean multiVote;

  @Column(nullable = false)
  private Boolean multiSubmission;

  @Column(nullable = false)
  private Boolean offer;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContestStateEntity state;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContestTypeEntity type;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<ContestTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
  private Set<ContestParticipationEntity> participation;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
  private List<ContestMediaEntity> uploads = new ArrayList<>();
}
