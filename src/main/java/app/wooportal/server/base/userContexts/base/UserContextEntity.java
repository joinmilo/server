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
import org.hibernate.annotations.Type;
import app.wooportal.server.base.addresses.base.AddressEntity;
import app.wooportal.server.base.contacts.ContactEntity;
import app.wooportal.server.base.userContexts.base.translations.UserContextTranslatableEntity;
import app.wooportal.server.base.userContexts.friend.FriendEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.features.articles.ratings.ArticleRatingEntity;
import app.wooportal.server.features.contests.participations.ContestParticipationEntity;
import app.wooportal.server.features.contests.votes.ContestVoteEntity;
import app.wooportal.server.features.deals.base.DealEntity;
import app.wooportal.server.features.events.attendee.AttendeeEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.events.comments.EventCommentEntity;
import app.wooportal.server.features.events.ratings.EventRatingEntity;
import app.wooportal.server.features.forms.formTemplate.UserFormTemplateEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
import app.wooportal.server.features.organisations.members.MemberEntity;
import app.wooportal.server.features.organisations.ratings.OrganisationRatingEntity;
import app.wooportal.server.features.surveys.assignments.AssignmentEntity;
import app.wooportal.server.features.surveys.result.SurveyResultEntity;
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
public class UserContextEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity profilePicture;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<ArticleRatingEntity> articleRating;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<AssignmentEntity> assignments;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<AttendeeEntity> attendee;
  
  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<ContestVoteEntity> contestVotes;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<ContestParticipationEntity> contestPariticpations;

  @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
  private Set<DealEntity> deals;

  @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
  private Set<EventEntity> events;
  
  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<EventCommentEntity> eventComment;
  
  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<EventRatingEntity> eventRating;

  @OneToMany(mappedBy = "addressee", fetch = FetchType.LAZY)
  private Set<FriendEntity> friendAddressee;

  @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
  private Set<FriendEntity> friendRequester;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<MemberEntity> member;
  
  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<OrganisationRatingEntity> organisationRating;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<SurveyResultEntity> surveyResults;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<UserContextTranslatableEntity> translatables;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<UserFormTemplateEntity> userFormTemplate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_context_contacts", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "contact_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "contact_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<ContactEntity> contacts = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_events", joinColumns = @JoinColumn(name = "user_context_id"),
  inverseJoinColumns = @JoinColumn(name = "event_id"),
  uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "event_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<EventEntity> favoriteEvents = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_deals", joinColumns = @JoinColumn(name = "user_context_id"),
  inverseJoinColumns = @JoinColumn(name = "deal_id"),
  uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "deal_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<EventEntity> favoriteOffers = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_organisations", joinColumns = @JoinColumn(name = "user_context_id"),
  inverseJoinColumns = @JoinColumn(name = "organisation_id"),
  uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "organisation_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<OrganisationEntity> favoriteOrganisations = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_context_uploads", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> uploads = new ArrayList<>();
}
