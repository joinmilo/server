package app.wooportal.server.base.external.app;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AppPredicateBuilder
    extends PredicateBuilder<QAppEntity, AppEntity> {

  public AppPredicateBuilder() {
    super(QAppEntity.appEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.url.likeIgnoreCase(term);
  }
}
