package app.milo.server.features.organisation.rating;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.base.analytics.rating.RatableEntity;
import app.milo.server.features.organisation.base.OrganisationEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "organisation_ratings", uniqueConstraints = 
@UniqueConstraint(columnNames = { "parent_id", "user_context_id" }))
public class OrganisationRatingEntity extends RatableEntity<OrganisationEntity> {

  private static final long serialVersionUID = 1L;

}
