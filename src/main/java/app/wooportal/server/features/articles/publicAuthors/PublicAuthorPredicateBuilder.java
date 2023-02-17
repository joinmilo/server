package app.wooportal.server.features.articles.publicAuthors;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.base.contacts.QContactEntity;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PublicAuthorPredicateBuilder extends PredicateBuilder<QContactEntity, PublicAuthorEntity> {

  public PublicAuthorPredicateBuilder() {
    super(QContactEntity.contactEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
