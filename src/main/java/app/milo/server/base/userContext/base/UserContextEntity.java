package app.milo.server.base.userContext.base;

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
import app.milo.server.base.address.base.AddressEntity;
import app.milo.server.base.contact.ContactEntity;
import app.milo.server.base.userContext.base.media.UserContextMediaEntity;
import app.milo.server.base.userContext.base.translations.UserContextTranslatableEntity;
import app.milo.server.base.userContext.friend.FriendEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.security.components.user.UserEntity;
import app.milo.server.features.article.components.base.ArticleEntity;
import app.milo.server.features.article.components.rating.ArticleRatingEntity;
import app.milo.server.features.contest.participation.ContestParticipationEntity;
import app.milo.server.features.contest.vote.ContestVoteEntity;
import app.milo.server.features.deal.components.base.DealEntity;
import app.milo.server.features.event.attendee.EventAttendeeEntity;
import app.milo.server.features.event.base.EventEntity;
import app.milo.server.features.event.comment.EventCommentEntity;
import app.milo.server.features.event.rating.EventRatingEntity;
import app.milo.server.features.form.formTemplate.UserFormTemplateEntity;
import app.milo.server.features.organisation.base.OrganisationEntity;
import app.milo.server.features.organisation.member.OrganisationMemberEntity;
import app.milo.server.features.organisation.rating.OrganisationRatingEntity;
import app.milo.server.features.survey.assignment.SurveyAssignmentEntity;
import app.milo.server.features.survey.result.SurveyResultEntity;
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
  private Set<SurveyAssignmentEntity> assignments;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<EventAttendeeEntity> attendedEvents;

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
  private Set<FriendEntity> receivedFriendRequests;

  @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
  private Set<FriendEntity> sentFriendRequests;

  @OneToMany(mappedBy = "userContext", fetch = FetchType.LAZY)
  private Set<OrganisationMemberEntity> members;

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
  @JoinTable(name = "favorite_articles", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "article_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "article_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<ArticleEntity> favoriteArticles = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_authors", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "author_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<UserContextEntity> favoriteAuthors = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_authors", joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "user_context_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "author_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<UserContextEntity> favoritingUsers = new ArrayList<>();
  
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
  private List<DealEntity> favoriteDeals = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_organisations", joinColumns = @JoinColumn(name = "user_context_id"),
      inverseJoinColumns = @JoinColumn(name = "organisation_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "organisation_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<OrganisationEntity> favoriteOrganisations = new ArrayList<>();

}
