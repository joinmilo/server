package app.wooportal.server.features.events.base;

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
import app.wooportal.server.base.addresses.base.AddressEntity;
import app.wooportal.server.base.contact.base.ContactEntity;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.events.attendee.AttendeeEntity;
import app.wooportal.server.features.events.attendeeConfiguration.AttendeeConfigurationEntity;
import app.wooportal.server.features.events.eventCategories.EventCategoryEntity;
import app.wooportal.server.features.events.eventRatings.EventRatingEntity;
import app.wooportal.server.features.events.eventTargetGroups.EventTargetGroupEntity;
import app.wooportal.server.features.events.eventVisitors.EventVisitorEntity;
import app.wooportal.server.features.events.translations.EventTranslatableEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
import app.wooportal.server.features.schedules.ScheduleEntity;
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

  private double entryFee;

  private String seoDescription;

  private String slug;

  @Column(nullable = false)
  private Boolean sponsored;

  private String videoChatLink;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.LAZY)
  private AttendeeConfigurationEntity attendeeConfiguration;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity cardImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity creator;

  @OneToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<AttendeeEntity> attendee;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<EventVisitorEntity> visitor;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<EventRatingEntity> rating;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<ScheduleEntity> schedules;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
  protected Set<EventTranslatableEntity> translatables;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "event_media", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "event_event_target_groups", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "event_target_group_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "event_target_group_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<EventTargetGroupEntity> eventEventTargetGroups = new ArrayList<>();

}
