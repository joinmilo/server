package app.milo.server.features.event.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.milo.server.base.address.base.AddressEntity;
import app.milo.server.base.contact.ContactEntity;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.seo.annotations.SlugSource;
import app.milo.server.core.seo.annotations.SlugTarget;
import app.milo.server.features.event.attendeeConfiguration.EventAttendeeConfigurationEntity;
import app.milo.server.features.event.base.translations.EventTranslatableEntity;
import app.milo.server.features.event.base.visitors.EventVisitorEntity;
import app.milo.server.features.event.category.EventCategoryEntity;
import app.milo.server.features.event.comment.EventCommentEntity;
import app.milo.server.features.event.media.EventMediaEntity;
import app.milo.server.features.event.rating.EventRatingEntity;
import app.milo.server.features.event.schedule.EventScheduleEntity;
import app.milo.server.features.event.targetGroup.EventTargetGroupEntity;
import app.milo.server.features.organisation.base.OrganisationEntity;
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
  
  private Boolean commentsAllowed;

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

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
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
  
  private List<EventTargetGroupEntity> targetGroups = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_events", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "user_context_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "event_id"})})
  
  private List<UserContextEntity> favoritingUsers = new ArrayList<>();
}
