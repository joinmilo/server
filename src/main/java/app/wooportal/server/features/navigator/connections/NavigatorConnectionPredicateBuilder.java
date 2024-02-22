package app.wooportal.server.features.navigator.connections;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NavigatorConnectionPredicateBuilder extends PredicateBuilder<QNavigatorConnectionEntity, NavigatorConnectionEntity> {

  public NavigatorConnectionPredicateBuilder() {
    super(QNavigatorConnectionEntity.navigatorConnectionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
