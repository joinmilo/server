package app.milo.server.features.organisation.configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.security.components.role.base.RoleEntity;
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
@Table(name = "organisation_configurations")
public class OrganisationConfigurationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean approvalRequired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private RoleEntity memberRole;

}
