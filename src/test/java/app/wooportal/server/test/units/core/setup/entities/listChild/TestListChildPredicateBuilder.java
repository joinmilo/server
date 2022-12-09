package app.wooportal.server.test.units.core.setup.entities.listChild;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class TestListChildPredicateBuilder extends PredicateBuilder<QTestListChildEntity, TestListChildEntity>{

  public TestListChildPredicateBuilder() {
    super(QTestListChildEntity.testListChildEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }


  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
