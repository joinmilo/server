package app.milo.server.base.address.suburb;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SuburbPredicateBuilder extends PredicateBuilder<QSuburbEntity, SuburbEntity> {

  public SuburbPredicateBuilder() {
    super(QSuburbEntity.suburbEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
