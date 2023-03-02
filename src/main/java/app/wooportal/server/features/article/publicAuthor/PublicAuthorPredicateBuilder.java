package app.wooportal.server.features.article.publicAuthor;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PublicAuthorPredicateBuilder extends PredicateBuilder<QPublicAuthorEntity, PublicAuthorEntity> {

  public PublicAuthorPredicateBuilder() {
    super(QPublicAuthorEntity.publicAuthorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
