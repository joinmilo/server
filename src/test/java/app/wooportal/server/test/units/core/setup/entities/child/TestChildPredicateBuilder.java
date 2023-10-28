package app.wooportal.server.test.units.core.setup.entities.child;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class TestChildPredicateBuilder extends PredicateBuilder<QTestChildEntity, TestChildEntity>{

  public TestChildPredicateBuilder() {
    super(QTestChildEntity.testDataChildEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

}
