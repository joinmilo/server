package app.wooportal.server.features.organisations.ratings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
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
@Table(name = "organisation_ratings")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class OrganisationRatingEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Integer score;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
}
