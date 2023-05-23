package app.wooportal.server.features.organisation.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationMediaPredicateBuilder
    extends PredicateBuilder<QOrganisationMediaEntity, OrganisationMediaEntity> {

  public OrganisationMediaPredicateBuilder() {
    super(QOrganisationMediaEntity.organisationMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
