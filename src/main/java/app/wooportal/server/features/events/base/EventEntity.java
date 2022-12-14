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
import com.fasterxml.jackson.annotation.JsonIgnore;
import app.wooportal.server.base.addresses.AddressEntity;
import app.wooportal.server.base.contact.base.ContactEntity;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.events.attendee.AttendeeEntity;
import app.wooportal.server.features.events.eventCategories.EventCategoryEntity;
import app.wooportal.server.features.events.eventRatings.EventRatingEntity;
import app.wooportal.server.features.events.eventVisitors.EventVisitorEntity;
import app.wooportal.server.features.events.translations.EventTranslatableEntity;
import app.wooportal.server.features.feedbacks.comment.CommentEntity;
import app.wooportal.server.features.feedbacks.rating.RatingEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
import app.wooportal.server.features.schedules.ScheduleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "events")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class EventEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<AttendeeEntity> attendee;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity cardImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private EventCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity creator;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "event_comment", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "comment_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "comment_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<CommentEntity> comments = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

  private double entryFee;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<EventVisitorEntity> visitor;

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<EventRatingEntity> rating;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "event_media", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();

  @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
  private Set<ScheduleEntity> schedules;

  private String seoDescription;

  private String slug;

  private Boolean sponsored;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "event_rating", joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "rating_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"event_id", "rating_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<RatingEntity> ratings = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
  @ToString.Exclude
  @JsonIgnore
  protected Set<EventTranslatableEntity> translatables;

  private String videoChatLink;

}
