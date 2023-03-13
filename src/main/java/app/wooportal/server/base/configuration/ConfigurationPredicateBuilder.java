package app.wooportal.server.base.configuration;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ConfigurationPredicateBuilder extends PredicateBuilder<QConfigurationEntity, ConfigurationEntity> {

  public ConfigurationPredicateBuilder() {
    super(QConfigurationEntity.configurationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term).or(query.value.likeIgnoreCase(term));
  }
}
