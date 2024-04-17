package app.milo.server.base.thirdparty.appPlatform;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class AppPlatformPredicateBuilder
    extends PredicateBuilder<QAppPlatformEntity, AppPlatformEntity> {

  public AppPlatformPredicateBuilder() {
    super(QAppPlatformEntity.appPlatformEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
