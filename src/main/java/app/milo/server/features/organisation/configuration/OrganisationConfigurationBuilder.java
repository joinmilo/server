package app.milo.server.features.organisation.configuration;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class OrganisationConfigurationBuilder extends PredicateBuilder<QOrganisationConfigurationEntity, OrganisationConfigurationEntity> {

  public OrganisationConfigurationBuilder() {
    super(QOrganisationConfigurationEntity.organisationConfigurationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
