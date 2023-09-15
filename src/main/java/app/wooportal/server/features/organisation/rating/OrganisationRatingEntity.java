package app.wooportal.server.features.organisation.rating;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.analytics.rating.RatableEntity;
import app.wooportal.server.features.organisation.base.OrganisationEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "organisation_ratings")
public class OrganisationRatingEntity extends RatableEntity<OrganisationEntity> {

  private static final long serialVersionUID = 1L;

}
