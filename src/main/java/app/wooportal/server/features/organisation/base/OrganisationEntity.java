package app.wooportal.server.features.organisation.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.address.base.AddressEntity;
import app.wooportal.server.base.contact.ContactEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.organisation.base.media.OrganisationMediaEntity;
import app.wooportal.server.features.organisation.base.translations.OrganisationTranslatableEntity;
import app.wooportal.server.features.organisation.base.visitors.OrganisationVisitorEntity;
import app.wooportal.server.features.organisation.comment.OrganisationCommentEntity;
import app.wooportal.server.features.organisation.member.MemberEntity;
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
  private String name;

  private String metaDescription;

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
  private Set<MemberEntity> members;

  @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
  private Set<OrganisationRatingEntity> ratings;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationTranslatableEntity> translatables;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
  private List<OrganisationMediaEntity> uploads = new ArrayList<>();

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationVisitorEntity> visitors;

}
