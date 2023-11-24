package app.wooportal.server.base.cms.plugin;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PluginPredicateBuilder extends PredicateBuilder<QPluginEntity, PluginEntity> {

  public PluginPredicateBuilder() {
    super(QPluginEntity.pluginEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.code.likeIgnoreCase(term).or(query.translatables.any().name.likeIgnoreCase(term));
  }
  public BooleanExpression withCode(String code) {
    return code != null && !code.isBlank() ? query.code.equalsIgnoreCase(code)
        : null;
  }
}
