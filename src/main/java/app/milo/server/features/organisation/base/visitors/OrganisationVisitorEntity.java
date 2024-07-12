package app.milo.server.features.organisation.base.visitors;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.visit.visitable.VisitableEntity;
import app.milo.server.features.organisation.base.OrganisationEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "organisation_visitors")
public class OrganisationVisitorEntity extends VisitableEntity<OrganisationEntity> {

  private static final long serialVersionUID = 1L;
}
