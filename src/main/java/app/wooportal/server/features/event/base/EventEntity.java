package app.wooportal.server.features.event.base;

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
import app.wooportal.server.base.address.base.AddressEntity;
import app.wooportal.server.base.contact.ContactEntity;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.seo.annotations.SlugSource;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.features.event.attendeeConfiguration.EventAttendeeConfigurationEntity;
import app.wooportal.server.features.event.base.media.EventMediaEntity;
import app.wooportal.server.features.event.base.translations.EventTranslatableEntity;
import app.wooportal.server.features.event.base.visitors.EventVisitorEntity;
import app.wooportal.server.features.event.category.EventCategoryEntity;
import app.wooportal.server.features.event.comment.EventCommentEntity;
import app.wooportal.server.features.event.rating.EventRatingEntity;
import app.wooportal.server.features.event.schedule.EventScheduleEntity;
import app.wooportal.server.features.event.targetGroup.EventTargetGroupEntity;
import app.wooportal.server.features.organisation.base.OrganisationEntity;
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
@Table(name = "events")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class EventEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String content;

  private Double entryFee;

  @Translatable
  @SlugSource
  private String name;

  @Translatable
  private String shortDescription;

  private String metaDescription;

  @Column(nullable = false, unique = true)
  @SlugTarget
  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;

  private String videoChatLink;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventAttendeeConfigurationEntity attendeeConfiguration;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity creator;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<EventCommentEntity> comments;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<EventVisitorEntity> visitors;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<EventRatingEntity> ratings;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<EventScheduleEntity> schedules;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<EventTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
  private Set<EventMediaEntity> uploads;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "event_event_target_groups", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "target_group_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "target_group_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<EventTargetGroupEntity> targetGroups = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_events_users", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "user_context_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "event_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<UserContextEntity> favoritingUsers = new ArrayList<>();

}
