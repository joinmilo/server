package app.milo.server.core.error.errorMessage;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ErrorMessagePredicateBuilder extends PredicateBuilder<QErrorMessageEntity, ErrorMessageEntity> {

  public ErrorMessagePredicateBuilder() {
    super(QErrorMessageEntity.errorMessageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }
}
