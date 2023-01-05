package app.wooportal.server.features.organisations.base;

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
import app.wooportal.server.base.addresses.base.AddressEntity;
import app.wooportal.server.base.contact.base.ContactEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.organisations.base.translations.OrganisationTranslatableEntity;
import app.wooportal.server.features.organisations.base.visitors.OrganisationVisitorEntity;
import app.wooportal.server.features.organisations.members.MemberEntity;
import app.wooportal.server.features.organisations.organisationComments.OrganisationCommentEntity;
import app.wooportal.server.features.organisations.organisationRatings.OrganisationRatingEntity;
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
@Table(name = "organisations")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class OrganisationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String seoDescription;

  private String slug;

  @Column(nullable = false)
  private Boolean approved;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity cardImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<EventEntity> events;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationVisitorEntity> organisationVisitors;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<OrganisationCommentEntity> organisationComments;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<MemberEntity> members;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<OrganisationRatingEntity> organisationRatings;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationTranslatableEntity> organisationTranslatable;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "organisation_media", joinColumns = @JoinColumn(name = "organisation_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"organisation_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();


}
