package app.wooportal.server.base.userContexts.base;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.addresses.AddressEntity;
import app.wooportal.server.base.contact.base.ContactEntity;
import app.wooportal.server.base.userContexts.friend.FriendEntity;
import app.wooportal.server.base.userContexts.translations.UserContextTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.features.contests.contestParticipations.ContestParticipationEntity;
import app.wooportal.server.features.contests.contestVotes.ContestVoteEntity;
import app.wooportal.server.features.deals.base.DealEntity;
import app.wooportal.server.features.events.attendee.AttendeeEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.events.eventComments.EventCommentEntity;
import app.wooportal.server.features.events.eventRatings.EventRatingEntity;
import app.wooportal.server.features.forms.userFormTemplate.UserFormTemplateEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
import app.wooportal.server.features.organisations.members.MemberEntity;
import app.wooportal.server.features.organisations.organisationRatings.OrganisationRatingEntity;
import app.wooportal.server.features.surveys.assignments.AssignmentEntity;
import app.wooportal.server.features.surveys.surveyResult.SurveyResultEntity;
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
@Table(name = "user_contexts")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class UserContextEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<AssignmentEntity> assignments;

  @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
  private Set<EventEntity> events;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<SurveyResultEntity> surveyResults;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<MemberEntity> member;

  @OneToMany(mappedBy = "addressee", fetch = FetchType.LAZY)
  private Set<FriendEntity> friendAddressee;

  @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
  private Set<FriendEntity> friendRequester;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<ContestVoteEntity> contestVotes;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<ContestParticipationEntity> contestPariticpations;

  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<UserContextTranslatableEntity> userContextTranslateables;

  @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
  private Set<DealEntity> deals;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<OrganisationRatingEntity> organisationRating;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_context_contacts", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "comment_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "contact_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<ContactEntity> contacts = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_context_uploads", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> uploads = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_organisations", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "organisation_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "organisation_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<OrganisationEntity> organisations = new ArrayList<>();

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<EventRatingEntity> eventRating;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<EventCommentEntity> eventComment;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<AttendeeEntity> attendee;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity title_image;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity profile_picture;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private UserFormTemplateEntity userFormTemplate;


}
