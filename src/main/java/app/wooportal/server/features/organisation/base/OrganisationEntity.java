package app.wooportal.server.features.organisation.base;

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
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.organisation.base.media.OrganisationMediaEntity;
import app.wooportal.server.features.organisation.base.translations.OrganisationTranslatableEntity;
import app.wooportal.server.features.organisation.base.visitors.OrganisationVisitorEntity;
import app.wooportal.server.features.organisation.comment.OrganisationCommentEntity;
import app.wooportal.server.features.organisation.member.OrganisationMemberEntity;
import app.wooportal.server.features.organisation.rating.OrganisationRatingEntity;
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

  @Column(nullable = false)
  private Boolean approved;

  @Translatable
  private String description;

  @Column(nullable = false)
  @SlugSource
  private String name;

  private String metaDescription;

  @SlugTarget
  private String slug;
  
  @Column(nullable = false)
  private Boolean sponsored;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.LAZY)
  private ContactEntity contact;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<EventEntity> events;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<OrganisationCommentEntity> comments;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<OrganisationMemberEntity> members;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<OrganisationRatingEntity> ratings;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
  private Set<OrganisationMediaEntity> uploads;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationVisitorEntity> visitors;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite_organisations_users", joinColumns = @JoinColumn(name = "organisation_id"),
      inverseJoinColumns = @JoinColumn(name = "user_context_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_context_id", "organisation_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<UserContextEntity> favoritingUsers = new ArrayList<>();
  
}
