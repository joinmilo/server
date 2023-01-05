package app.wooportal.server.base.developers;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class DeveloperPredicateBuilder extends PredicateBuilder<QDeveloperEntity, DeveloperEntity> {

  public DeveloperPredicateBuilder() {
    super(QDeveloperEntity.developerEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
