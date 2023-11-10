package app.wooportal.server.features.contest.base;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.contact.ContactEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.seo.annotations.SlugSource;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.features.contest.base.media.ContestMediaEntity;
import app.wooportal.server.features.contest.base.translations.ContestTranslatableEntity;
import app.wooportal.server.features.contest.comment.ContestCommentEntity;
import app.wooportal.server.features.contest.participation.ContestParticipationEntity;
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
  
  @Column(nullable = false)
  private Integer max_votes;
  
  @Column(nullable = false)
  private Integer max_submissions;
  
  private String metaDescription;
  
  @Translatable
  @SlugSource
  private String name;

  @Column(nullable = false)
  private Boolean offer;
  
  @Column(nullable = false)
  private OffsetDateTime participationEndDate;
  
  private Boolean participationApproval;
  
  private Boolean canParticipantVote;
  
  @Column(nullable = false, unique = true)
  @SlugTarget
  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;
  
  @Translatable
  private String shortDescription;
  
  private OffsetDateTime voteEndDate;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ContestTypeEntity type;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<ContestTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
  private Set<ContestParticipationEntity> participations;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
  private Set<ContestMediaEntity> uploads;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "contest")
  private Set<ContestCommentEntity> comments;
}
