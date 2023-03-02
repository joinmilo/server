package app.wooportal.server.base.external.appStore;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AppStorePredicateBuilder
    extends PredicateBuilder<QAppStoreEntity, AppStoreEntity> {

  public AppStorePredicateBuilder() {
    super(QAppStoreEntity.appStoreEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
