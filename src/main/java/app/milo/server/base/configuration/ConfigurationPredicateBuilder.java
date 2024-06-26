package app.milo.server.base.configuration;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ConfigurationPredicateBuilder extends PredicateBuilder<QConfigurationEntity, ConfigurationEntity> {

  public ConfigurationPredicateBuilder() {
    super(QConfigurationEntity.configurationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.code.likeIgnoreCase(term).or(query.value.likeIgnoreCase(term));
  }

  public BooleanExpression withCode(String code) {
    return query.code.equalsIgnoreCase(code);
  }
}
