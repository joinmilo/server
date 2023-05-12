package app.wooportal.server.features.deal.base;

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
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.deal.base.translations.DealTranslatableEntity;
import app.wooportal.server.features.deal.base.visitors.DealVisitorEntity;
import app.wooportal.server.features.deal.category.DealCategoryEntity;
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
@Table(name = "deals")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class DealEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String description;
  
  @Translatable
  private String name;
  
  @Translatable
  private String shortDescription;

  private String metaDescription;

  @Column(nullable = false, unique = true)
  private String slug;

  private double price;

  @Column(nullable = false)
  private Boolean sponsored;

  @Column(nullable = false)
  private Boolean offer;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity creator;

  @ManyToOne(fetch = FetchType.LAZY)
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity cardImage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private DealCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ContactEntity contact;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<DealVisitorEntity> visitors;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  protected Set<DealTranslatableEntity> translatables;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "deal_media", joinColumns = @JoinColumn(name = "deal_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"deal_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();
}
