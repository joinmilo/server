package app.wooportal.server.base.userContext.base;

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
import app.wooportal.server.base.address.base.AddressEntity;
import app.wooportal.server.base.contact.ContactEntity;
import app.wooportal.server.base.userContext.base.media.UserContextMediaEntity;
import app.wooportal.server.base.userContext.base.translations.UserContextTranslatableEntity;
import app.wooportal.server.base.userContext.friend.FriendEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.features.article.base.ArticleEntity;
import app.wooportal.server.features.article.rating.ArticleRatingEntity;
import app.wooportal.server.features.contest.participation.ContestParticipationEntity;
import app.wooportal.server.features.contest.vote.ContestVoteEntity;
import app.wooportal.server.features.deal.base.DealEntity;
import app.wooportal.server.features.event.attendee.AttendeeEntity;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.comment.EventCommentEntity;
import app.wooportal.server.features.event.rating.EventRatingEntity;
import app.wooportal.server.features.form.formTemplate.UserFormTemplateEntity;
import app.wooportal.server.features.organisation.base.OrganisationEntity;
import app.wooportal.server.features.organisation.member.MemberEntity;
import app.wooportal.server.features.organisation.rating.OrganisationRatingEntity;
import app.wooportal.server.features.survey.assignment.AssignmentEntity;
import app.wooportal.server.features.survey.result.SurveyResultEntity;
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
  
  @Translatable
  private String description;
  
  
  //TOOD: slugify
  private String slug;

  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  private Set<ArticleEntity> articles;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<ArticleRatingEntity> articleRatings;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<AssignmentEntity> assignments;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<AttendeeEntity> attendedEvents;

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
  private Set<EventRatingEntity> eventRatings;

  @OneToMany(mappedBy = "addressee", fetch = FetchType.LAZY)
  private Set<FriendEntity> friendAddressee;

  @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
  private Set<FriendEntity> friendRequester;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<MemberEntity> member;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<OrganisationRatingEntity> organisationRatings;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<SurveyResultEntity> surveyResults;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<UserContextTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "userContext")
  private Set<UserContextMediaEntity> uploads;
  
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
}
